package server;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpServerTest {

	private HttpServer httpServer;

	@BeforeEach
	void init() {
		httpServer = new HttpServer();
	}

	/**
	 * ����������� ����� ������� ��������:
	 * �� http-������� ��������� �������� ������� � �������� ������.
	 * �������� ��� �������� �����-�� �����.
	 */
	@Test
	void getAnyAnswer() {
		String request = "GET / HTTP/1.0\n" +
				"Host: kors-server.ru";
		String response = HttpServer.getResponse(request);
		assertTrue(response != null, "Couldn't get any http response message.");
	}

}
