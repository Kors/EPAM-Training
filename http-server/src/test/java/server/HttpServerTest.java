package server;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

@Log4j2
class HttpServerTest {

	private int PORT = 8080;
	private String REQUEST = "GET / HTTP/1.1\r\n\r\n"; // TODO нормальный запрос сделать

	@Test
	void ping() throws IOException {
		Thread serverThread = new Thread(HttpServer::main);
		serverThread.start();
		try (Socket socket = new Socket("localhost", PORT)) {
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os.write(REQUEST.getBytes());

			String line;
			while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
				// TODO читаем ответ, надо проверять
				log.info(line);
			}
		}
		serverThread.interrupt();
	}

}
