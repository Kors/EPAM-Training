import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HttpServerTest {

	private HttpServer httpServer;

	@Before
	public void init() {
		httpServer = new HttpServer();
		httpServer.init();
	}

	/**
	 * Предположим самую простую ситуацию:
	 * От http-сервера требуется получать запросы и отдавать ответы.
	 * Проверим что получаем какой-то ответ.
	 */
	@Test
	public void getAnyAnswer() {
		HttpServer httpServer = new HttpServer();
		String request = "GET / HTTP/1.0\n" +
				"Host: kors-server.ru";
		String response = httpServer.getResponse(request);
		assertTrue("Couldn't get any http response message.", response != null);
	}

}
