package server;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Log4j2
class HttpServer {

	private static final int PORT = 8080;

	public static void main(String[] args) throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			log.info(() -> "Server started on port: " + serverSocket.getLocalPort() + "\n");
			//noinspection InfiniteLoopStatement
			while (true) {
				try (Socket clientSocket = serverSocket.accept()) {
					log.debug("Client connected");
					new SocketProcessor(clientSocket).run();
					log.debug("done");
				} catch (IOException e) {
					log.error("Connection failed", e);
				}
			}
		}
	}
}
