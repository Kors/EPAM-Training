package server;

import java.util.Map;

public interface HttpRequest {

	HttpMethod getMethod();

	String getPath();

	Map<String, String> getParams();

	Map<String, String> getHeaders();

	default String getHostAndPort() {
		return getHeaders().get("Host");
	}

	String getBody();
}
