package server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class HelloWorldResponder extends SocketProcessor {

	final static String httpMsg = "<html><body><h1>La-la-la! It works and I'm happy!!! =)</h1></body></html>";

	HelloWorldResponder(Socket socket) throws IOException {
		super(socket);
	}

	@Override
	Map<String, String> getHeaderProps(HttpRequest httpRequest) {
		Map<String, String> m = new HashMap<>();
		m.put("code", "200 OK");
		m.put("contentType", "text/html");
		m.put("length", String.valueOf(httpMsg.getBytes().length));
		return m;
	}

	@Override
	void writePage(HttpRequest httpRequest) throws IOException {
		outputStream.write(httpMsg.getBytes());
	}
}
