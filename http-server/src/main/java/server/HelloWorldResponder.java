package server;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.Socket;

class HelloWorldResponder extends SocketProcessor {

	HelloWorldResponder(Socket socket) throws IOException {
		super(socket);
	}

	@SneakyThrows
	@Override
	void writePage(HttpRequest httpRequest) {
		outputStream.write("<html><body><h1>La-la-la! It works and I'm happy!!! =)</h1></body></html>".getBytes());
	}
}
