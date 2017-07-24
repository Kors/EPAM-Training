package server;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

@Log4j2
class HttpServerTest {

	private static Thread serverThread;

	private int PORT = 8080;
	private String REQUEST = "GET /testPage.html HTTP/1.1\r\n\r\n"; // TODO нормальный запрос сделать

	@BeforeAll
	static void setUp() {
		serverThread = new Thread(HttpServer::main, "httpServer");
		serverThread.start();
	}

	@AfterAll
	static void tearDown() {
		serverThread.interrupt();
	}

	@Test
	void ping() throws IOException {
		try (Socket socket = new Socket("localhost", PORT)) {
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os.write(REQUEST.getBytes());

			String line;
			while ((line = br.readLine()) != null) {
				// TODO читаем ответ, надо проверять
				log.info(line);
			}
		}
	}

}
