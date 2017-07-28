package server;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.StringStartsWith;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
class HttpServerTest {

	private static Thread serverThread;
	private Socket clientSocket;

	private BufferedWriter clientWriter;
	private BufferedReader clientReader;

	private static int PORT;
	private static final String[] OK_REQUESTS = {
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
					"Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4\n\n",
			"HEAD /imgs/simpleImg.jpg HTTP/1.1\n" +
					"Host: localhost:8090\n" +
					"Connection: keep-alive\n" +
					"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36\n" +
					"Accept: image/webp,image/apng,image/*,*/*;q=0.8\n" +
					"Referer: http://localhost:8090/testPage.html\n" +
					"Accept-Encoding: gzip, deflate, br\n" +
					"Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4\n\n"
	};
	private static final String[] ERR_REQUESTS = {
			"GET /noSuchFile.txt HTTP/1.1\n" +
					"Host: localhost:8090\n" +
					"Connection: keep-alive\n" +
					"Cache-Control: max-age=0\n" +
					"Upgrade-Insecure-Requests: 1\n" +
					"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36\n" +
					"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\n" +
					"Accept-Encoding: gzip, deflate, br\n" +
					"Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4\n" +
					"If-Modified-Since: Wed, 21 Oct 2015 07:28:00 GMT\n\n",
			"POST /imgs/simpleImg.jpg HTTP/1.1\n" +
					"Host: localhost:8090\n" +
					"Connection: keep-alive\n" +
					"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36\n" +
					"Accept: image/webp,image/apng,image/*,*/*;q=0.8\n" +
					"Referer: http://localhost:8090/testPage.html\n" +
					"Accept-Encoding: gzip, deflate, br\n" +
					"Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4\n\n" +
					"Some data to Post\n"
	};

	@BeforeAll
	static void setUp() {
		serverThread = new Thread(HttpServer::main, "httpServer");
		serverThread.start();
		PORT = getPort();
	}

	@SneakyThrows
	private static int getPort() {
		Properties props = new Properties();
		props.load(HttpServer.class.getResourceAsStream("/http-server.properties"));
		return Integer.parseInt(props.getProperty("port", "8080"));
	}

	@AfterAll
	static void tearDown() {
		serverThread.interrupt();
	}

	@BeforeEach
	void setUpEach() throws IOException {
		clientSocket = new Socket("localhost", PORT);
		clientWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	@AfterEach
	void tearDownEach() throws IOException {
		clientSocket.close();
	}

	@DisplayName("Get positive response")
	@ParameterizedTest
	@MethodSource(names = "getOkRequests")
	void okResponseTest(String request) throws IOException {
		clientWriter.write(request);
		clientWriter.flush();
		assertThat(getResponse(), StringStartsWith.startsWith("HTTP/1.1 200 OK"));
	}

	@DisplayName("Get negative response")
	@ParameterizedTest
	@MethodSource(names = "getErrRequests")
	void errResponseTest(String request) throws IOException {
		clientWriter.write(request);
		clientWriter.flush();

		String response = getResponse();
		assertThat(response, AnyOf.anyOf(StringStartsWith.startsWith("HTTP/1.1 404 Not Found"),
				StringStartsWith.startsWith("HTTP/1.1 501 Not Implemented")));
		assertThat("Negative response body is not empty", getBody(response), IsEqual.equalTo(""));
	}

	private String getBody(String response) {
		Matcher m = Pattern.compile(".*\\n\\s*\\n((.*\\n)*.*)").matcher(response);
		assertTrue(m.find(), "No body found! (No separator after head of response!)");
		return m.group(1);
	}

	private String getResponse() throws IOException {
		StringBuilder response = new StringBuilder();
		for (String line; (line = clientReader.readLine()) != null; ) {
			response.append(line).append("\n");
			log.debug(line);
		}
		return response.toString();
	}

	@SuppressWarnings("unused")
	private static String[] getOkRequests() {
		return OK_REQUESTS;
	}

	@SuppressWarnings("unused")
	private static String[] getErrRequests() {
		return ERR_REQUESTS;
	}

}
