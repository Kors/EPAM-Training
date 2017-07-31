package server;

import lombok.extern.log4j.Log4j2;
import lombok.val;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public abstract class SocketProcessor implements Runnable {

	final OutputStream outputStream;
	private final InputStream inputStream;
	private Socket socket;

	SocketProcessor(Socket socket) throws IOException {
		this.socket = socket;
		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
	}

	static String formatHeader(String code, String contentType, String length, String date) {
		return String.format(
				"HTTP/1.1 %s%n" +
						"Server: kors-server%n" +
						"Content-Type: %s%n" +
						"Content-Length: %s%n" +
						"Last-Modified: %s%n" +
						"Connection: close%n%n",
				code,
				contentType,
				length,
				date);
	}

	@Override
	public void run() {
		try (@SuppressWarnings("unused") Socket clientSocket = socket) {
			writeResponse(tryGetHttpRequest());
		} catch (IOException e) {
			log.error(e, e);
		}
	}

	private HttpRequest tryGetHttpRequest() {
		try {
			return getHttpRequest();
		} catch (Exception e) {
			log.error(e, e);
			return emptyRequest();
		}
	}

	private HttpRequest emptyRequest() {
		return HttpRequest.from(HttpMethod.UNKNOWN, "/", Collections.emptyMap(), Collections.emptyMap(), "");
	}

	private HttpRequest getHttpRequest() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String s = br.readLine();
		if (s == null)
			return emptyRequest();
		s = s.trim();

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

		HttpRequest request = HttpRequest.from(httpMethod, path, params, headers, body.toString());
		log.debug(request);
		return request;
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
		log.debug("Обработка завершена");
	}

	abstract Map<String, String> getHeaderProps(HttpRequest httpRequest);

	private void writeHeader(Map<String, String> headerProps) throws IOException {
		outputStream.write(formatHeader(
				headerProps.getOrDefault("code", "200 OK"),
				headerProps.getOrDefault("contentType", "text/html"),
				headerProps.getOrDefault("length", "1024"),
				headerProps.getOrDefault("lastModified", "Wed, 21 Oct 2015 07:28:00 GMT")
		).getBytes());
	}

	abstract void writePage(HttpRequest httpRequest) throws IOException;
}
