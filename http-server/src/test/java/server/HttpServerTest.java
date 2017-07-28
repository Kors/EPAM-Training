package server;

import lombok.extern.log4j.Log4j2;
import org.hamcrest.core.StringStartsWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;

@Log4j2
class HttpServerTest {

	private static Thread serverThread;

	private static final int PORT = 8080;
	private static final String[] REQUESTS = {
			"GET / HTTP/1.1\n" +
					"Host: localhost:8090\n" +
					"Connection: keep-alive\n" +
					"Cache-Control: max-age=0\n" +
					"Upgrade-Insecure-Requests: 1\n" +
					"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36\n" +
					"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\n" +
					"Accept-Encoding: gzip, deflate, br\n" +
					"Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4\n" +
					"If-Modified-Since: Wed, 21 Oct 2015 07:28:00 GMT\n\n",
			"GET /imgs/simpleImg.jpg HTTP/1.1\n" +
					"Host: localhost:8090\n" +
					"Connection: keep-alive\n" +
					"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36\n" +
					"Accept: image/webp,image/apng,image/*,*/*;q=0.8\n" +
					"Referer: http://localhost:8090/testPage.html\n" +
					"Accept-Encoding: gzip, deflate, br\n" +
					"Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4\n\n"
	};

	@BeforeAll
	static void setUp() {
		serverThread = new Thread(HttpServer::main, "httpServer");
		serverThread.start();
	}

	@AfterAll
	static void tearDown() {
		serverThread.interrupt();
	}

	@DisplayName("Get right response")
	@ParameterizedTest
	@MethodSource(names = "getRequests")
	void okResponseTest(String request) throws IOException {
		try (Socket socket = new Socket("localhost", PORT)) {
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os.write(request.getBytes());

			StringBuilder response = new StringBuilder();
			for (String line; (line = br.readLine()) != null; log.info(line)) {
				response.append(line);
			}
			assertThat(response.toString(), StringStartsWith.startsWith("HTTP/1.1 200 OK"));
		}
	}

	private static String[] getRequests() {
		return REQUESTS;
	}

}
