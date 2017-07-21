package server;


import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpServerTest {

	private HttpServer httpServer;

	@BeforeEach
	void init() {
		httpServer = new HttpServer();
	}

	/**
	 * Предположим самую простую ситуацию:
	 * От http-сервера требуется получать запросы и отдавать ответы.
	 * Проверим что получаем какой-то ответ.
	 */
	@Test
	void getAnyAnswer() {
		String request = "GET / HTTP/1.0\n" +
				"Host: kors-server.ru";
		String response = HttpServer.getResponse(request);
		assertTrue(response != null, "Couldn't get any http response message.");
	}


	/**
	 * Тест на соответствие ответа ожидаемому
	 */
	@Test
	void simpleResponse() throws Exception {
		Socket mockSocket = Mockito.mock(Socket.class);
		ByteOutputStream testStream = new ByteOutputStream();
		Mockito.when(mockSocket.getOutputStream()).thenReturn(testStream);

		HttpServer.writeResponse(mockSocket);
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
