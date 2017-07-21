package server;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.OutputStream;
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
					writeResponse(clientSocket);
					log.debug("done");
				} catch (IOException e) {
					log.error("Connection failed", e);
				}
			}
		}
	}

	static void writeResponse(Socket socket) throws IOException {
		OutputStream outputStream = socket.getOutputStream();
		writeHeader(outputStream, 73);
		writePage(outputStream, "some request from client");
		outputStream.flush();
	}

	private static void writeHeader(OutputStream outputStream, int length) throws IOException {
		outputStream.write(String.format("HTTP/1.1 200 OK\r\n" +
				"Server: kors-server\r\n" +
				"Content-Type: text/html\r\n" +
				"Content-Length: %d\r\n" +
				"Connection: close\r\n\r\n", length).getBytes());
	}

	private static void writePage(OutputStream outputStream, String request) throws IOException {
		outputStream.write("<html><body><h1>La-la-la! It works and I'm happy!!! =)</h1></body></html>".getBytes());
	}
}
