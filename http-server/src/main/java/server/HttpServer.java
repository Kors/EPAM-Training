package server;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Log4j2
class HttpServer {

	private static final int PORT = 8080;

	public static void main(String... args) {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			log.info(() -> "Server started on port: " + serverSocket.getLocalPort() + "\n");
			while (!Thread.currentThread().isInterrupted()) {
				try (Socket clientSocket = serverSocket.accept()) {
					log.debug("Client connected");
					new Thread(new SocketProcessor(clientSocket)).start();
					log.debug("done");
				} catch (IOException e) {
					log.error("Connection failed", e);
				}
			}
		} catch (IOException e) {
			log.error("Start http-server failed", e);
		}
	}
}
