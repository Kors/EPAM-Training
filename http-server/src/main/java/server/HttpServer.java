package server;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ForkJoinPool;

@Log4j2
class HttpServer {

	private static final int PORT = 8080;

	public static void main(String... args) {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			log.info(() -> "Server started on port: " + serverSocket.getLocalPort() + "\n");
			ForkJoinPool pool = ForkJoinPool.commonPool();
			while (!Thread.currentThread().isInterrupted()) {
				log.debug("Client connected");
				pool.execute(new SocketProcessor(serverSocket.accept()));
			}
		} catch (IOException e) {
			log.error("Start http-server failed", e);
		}
	}
}
