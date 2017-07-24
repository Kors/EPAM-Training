package server;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;

class SocketProcessorTest {

	static final String REQUEST =
			"GET http://www.kors-server.ru/news.html?login=Ilya%20Korsikov&password=VeryStrongPassword HTTP/1.1\\r\\n" +
					"Host: www.site.ru\\r\\n" +
					"Referer: http://www.site.ru/index.html\\r\\n" +
					"Cookie: income=1\\r\\n" +
					"\\r\\n";

	static final String RESPONSE_TITLE =
			"HTTP/1.1 200 OK\r\n" +
					"Server: kors-server\r\n" +
					"Content-Type: text/html\r\n" +
					"Content-Length: 73\r\n" +
					"Connection: close\r\n\r\n";

	private Socket mockSocket;
	private ByteInputStream testInputStream;
	private ByteOutputStream testOutputStream;

	@BeforeEach
	void setUp() throws IOException {
		mockSocket = Mockito.mock(Socket.class);

		testInputStream = new ByteInputStream();
		testInputStream.setBuf(REQUEST.getBytes());
		Mockito.when(mockSocket.getInputStream()).thenReturn(testInputStream);

		testOutputStream = new ByteOutputStream();
		Mockito.when(mockSocket.getOutputStream()).thenReturn(testOutputStream);
	}

	/**
	 * “ест на соответствие ответа ожидаемому
	 */
	@Test
	void simpleResponse() throws Exception {
		SocketProcessor processor = new HelloWorldResponder(mockSocket);
		processor.run();
		assertThat(new String(testOutputStream.getBytes()).trim(),
				Is.is(RESPONSE_TITLE +
						"<html><body><h1>" +
						"La-la-la! It works and I'm happy!!! =)" +
						"</h1></body></html>"
				));
	}

	@Test
	void fileResponse() throws Exception {
		FileChannel fc = FileChannel.open(Paths.get(SocketProcessor.class.getResource("/testPage.html").toURI()));
		ByteBuffer b = ByteBuffer.allocate(1024);
		b.put(RESPONSE_TITLE.getBytes());
		fc.read(b);
		SocketProcessor processor = new FilesOnlyResponder(mockSocket);
		processor.run();
		assertThat(new String(testOutputStream.getBytes()),
				Is.is(new String(b.array())));
	}

}