package server;


import org.junit.jupiter.api.BeforeEach;

class HttpServerTest {

	private HttpServer httpServer;

	@BeforeEach
	void init() {
		httpServer = new HttpServer();
	}

}
