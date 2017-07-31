package server;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ForkJoinPool;

@Log4j2
class HttpServer {

	private final static Properties props = new Properties();

	public static void main(String... args) {
		loadProps();
		try (ServerSocket serverSocket = new ServerSocket(getPort())) {
			log.info(() -> "Server started, please visit: http://localhost:" + serverSocket.getLocalPort() + "\n");
			ForkJoinPool pool = ForkJoinPool.commonPool();
			while (!Thread.currentThread().isInterrupted()) {
				pool.execute(getExecutor(serverSocket.accept()));
				log.debug("Client connected");
			}
		} catch (IOException e) {
			log.error("Start http-server failed", e);
		}
	}

	private static void loadProps() {
		try {
			props.load(HttpServer.class.getResourceAsStream("/http-server.properties"));
		} catch (IOException e) {
			log.error("Loading properties failed", e);
		}
	}

	private static int getPort() {
		return Integer.parseInt(props.getProperty("port", "8080"));
	}

	private static Runnable getExecutor(Socket socket) {
		try {
			return new FilesOnlyResponder(socket,
					props.getProperty("web.directory"));
		} catch (IOException e) {
			log.error("Executor can't be created", e);
			return () -> log.debug("empty executor");
		}
	}
}
