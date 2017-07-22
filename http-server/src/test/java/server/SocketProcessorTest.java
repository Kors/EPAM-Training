package server;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;

class SocketProcessorTest {

	private Socket mockSocket;
	private SocketProcessor processor;

	@BeforeEach
	void setUp() throws IOException {
		mockSocket = Mockito.mock(Socket.class);
		processor = new SocketProcessor(mockSocket);
	}

	/**
	 * “ест на соответствие ответа ожидаемому
	 */
	@Test
	void simpleResponse() throws Exception {
		ByteOutputStream testStream = new ByteOutputStream();
		Mockito.when(mockSocket.getOutputStream()).thenReturn(testStream);
		processor.run();
		assertThat(new String(testStream.getBytes()).trim(),
				Is.is("HTTP/1.1 200 OK\r\n" +
						"Server: kors-server\r\n" +
						"Content-Type: text/html\r\n" +
						"Content-Length: 73\r\n" +
						"Connection: close\r\n\r\n" +
						"<html><body><h1>" +
						"La-la-la! It works and I'm happy!!! =)" +
						"</h1></body></html>"
				));
	}

}