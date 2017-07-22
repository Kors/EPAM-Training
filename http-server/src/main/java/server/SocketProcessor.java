package server;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

@Log4j2
public class SocketProcessor implements Runnable {

	private Socket socket;
	private OutputStream outputStream;

	SocketProcessor(Socket socket) throws IOException {
		this.socket = socket;
		outputStream = socket.getOutputStream();
	}

	@Override
	public void run() {
		try (Socket clientSocket = socket) {
			writeHeader(73);
			writePage("some request from client");
			outputStream.flush();
		} catch (IOException e) {
			log.error(e);
		}
	}

	private void writeHeader(int length) throws IOException {
		outputStream.write(String.format("HTTP/1.1 200 OK\r\n" +
				"Server: kors-server\r\n" +
				"Content-Type: text/html\r\n" +
				"Content-Length: %d\r\n" +
				"Connection: close\r\n\r\n", length).getBytes());
	}

	private void writePage(String request) throws IOException {
		outputStream.write("<html><body><h1>La-la-la! It works and I'm happy!!! =)</h1></body></html>".getBytes());
	}
}
