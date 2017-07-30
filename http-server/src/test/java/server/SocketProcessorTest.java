package server;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;

class SocketProcessorTest {
	private final static Properties props = new Properties();

	private static final String REQUEST =
			"GET %s?login=Ilya%%20Korsikov&password=VeryStrongPassword HTTP/1.1\\r\\n" +
					"Host: www.site.ru\\r\\n" +
					"Referer: http://www.site.ru/index.html\\r\\n" +
					"Cookie: income=1\\r\\n" +
					"\\r\\n";

	private Socket mockSocket;
	private ByteInputStream testInputStream;
	private ByteOutputStream testOutputStream;

	@BeforeAll
	static void setUp() throws IOException {
		props.load(HttpServer.class.getResourceAsStream("/http-server.properties"));
	}


	@BeforeEach
	void setUpEach() throws IOException {
		mockSocket = Mockito.mock(Socket.class);

		testInputStream = new ByteInputStream();
		Mockito.when(mockSocket.getInputStream()).thenReturn(testInputStream);

		testOutputStream = new ByteOutputStream();
		Mockito.when(mockSocket.getOutputStream()).thenReturn(testOutputStream);
	}

	@DisplayName("simple 'Hello World' response")
	@Test
	void simpleResponse() throws Exception {
		testInputStream.setBuf(String.format(REQUEST, "/testPage.html").getBytes());
		SocketProcessor processor = new HelloWorldResponder(mockSocket);
		processor.run();
		assertThat(new String(testOutputStream.getBytes()).trim(),
				Is.is(SocketProcessor.formatHeader("200 OK", "text/html", "73", "Wed, 21 Oct 2015 07:28:00 GMT") +
						HelloWorldResponder.httpMsg
				));
	}

	@DisplayName("Correct files loading")
	@ParameterizedTest
	@CsvSource({"/testPage.html, text/html", "/imgs/simpleImg.jpg, image/jpeg", "/favicon.ico, image/x-icon"})
	void fileResponse(String file, String mimeType) throws Exception {
		testInputStream.setBuf(String.format(REQUEST, file).getBytes());
		Path p = Paths.get(props.getProperty("web.directory") + file);
		FileChannel fc = FileChannel.open(p);
		ByteBuffer b = ByteBuffer.allocate(1024 * 10);
		b.put(SocketProcessor.formatHeader("200 OK",
				mimeType,
				String.valueOf(p.toFile().length()),
				FilesOnlyResponder.getLastModifiedTime(p.toFile()))
				.getBytes());
		fc.read(b);
		SocketProcessor processor = new FilesOnlyResponder(mockSocket, props.getProperty("web.directory"));
		processor.run();
		assertThat(new String(testOutputStream.getBytes()).trim(),
				IsEqual.equalTo(new String(b.array()).trim()));
	}

	@DisplayName("Bad request format")
	@Test
	void unknownRequestFileResponse() throws Exception {
		testInputStream.setBuf("!&$.@?&/1.1_some bad request".getBytes());
		ByteBuffer b = ByteBuffer.allocate(1024);
		b.put(SocketProcessor.formatHeader("501 Not Implemented", "text/html",
				"0", "Wed, 21 Oct 2015 07:28:00 GMT").getBytes());
		new FilesOnlyResponder(mockSocket, props.getProperty("web.directory")).run();
		assertThat(new String(testOutputStream.getBytes()).trim(),
				IsEqual.equalTo(new String(b.array()).trim()));
	}

}