package server;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public abstract class SocketProcessor implements Runnable {

	private Socket socket;
	private final InputStream inputStream;
	final OutputStream outputStream;

	SocketProcessor(Socket socket) throws IOException {
		this.socket = socket;
		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
	}

	@Override
	public void run() {
		try (Socket clientSocket = socket) {
			writeResponse(getHttpRequest());
		} catch (IOException e) {
			log.error(e);
		}
	}

	@SneakyThrows
	private HttpRequest getHttpRequest() {
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String s = br.readLine().trim();

		String[] contentAndTail = s.split("\\s", 2);
		HttpMethod httpMethod = HttpMethod.valueOf(contentAndTail[0]);

		contentAndTail = contentAndTail[1].split("[?\\s]", 2);
		String path = contentAndTail[0];

		Map<String, String> params = contentAndTail[1].startsWith("HTTP") ?
				Collections.emptyMap() :
				getParams(contentAndTail[1].split("\\s", 2)[0]);

		val headers = new HashMap<String, String>();
		while ((s = br.readLine()) != null && !s.trim().isEmpty()) {
			String[] header = s.split(":\\s", 2);
			headers.put(header[0], header[1]);
		}

		val body = new StringBuilder();
		while (br.ready() && (s = br.readLine()) != null)
			body.append(s);

		return HttpRequest.from(httpMethod, path, params, headers, body.toString());
	}

	private Map<String, String> getParams(String s) {
		val params = new HashMap<String, String>();
		for (String param : s.split("&")) {
			String[] split = param.split("=", 2);
			params.put(split[0], split[1]);
		}
		return params;
	}

	private void writeResponse(HttpRequest httpRequest) throws IOException {
		writeHeader(getHeaderProps(httpRequest));
		writePage(httpRequest);
		outputStream.flush();
	}

	abstract Map<String, String> getHeaderProps(HttpRequest httpRequest);

	private void writeHeader(Map<String, String> headerProps) throws IOException {
		outputStream.write(formatHeader(
				headerProps.getOrDefault("code", "200 OK"),
				headerProps.getOrDefault("contentType", "text/html"),
				headerProps.getOrDefault("length", "1024")).getBytes());
	}

	static String formatHeader(String code, String contentType, String length) {
		return String.format(
				"HTTP/1.1 %s\r\n" +
						"Server: kors-server\r\n" +
						"Content-Type: %s\r\n" +
						"Content-Length: %s\r\n" +
						"Connection: close\r\n\r\n",
				code,
				contentType,
				length);
	}

	abstract void writePage(HttpRequest httpRequest);
}
