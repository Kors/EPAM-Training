package server;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import static server.HttpMethod.GET;
import static server.HttpMethod.HEAD;

@Log4j2
class FilesOnlyResponder extends SocketProcessor {

	private final EnumSet<HttpMethod> supportMethods = EnumSet.of(GET, HEAD);
	private File file;

	FilesOnlyResponder(Socket socket) throws IOException {
		super(socket);
	}

	@Override
	Map<String, String> getHeaderProps(HttpRequest httpRequest) {
		Map<String, String> m = new HashMap<>();
		m.put("contentType", "text/html");
		m.put("length", "0");
		if (!supportMethods.contains(httpRequest.getMethod())) {
			m.put("code", "501 Not Implemented");
			return m;
		}
		if (getFile(httpRequest).exists()) {
			m.put("code", "200 OK");
			m.put("length", String.valueOf(file.length()));
		} else {
			m.put("code", "404 Not Found");
		}
		return m;
	}

	private File getFile(HttpRequest httpRequest) {
		if (file != null)
			return file;
		String path = httpRequest.getPath();
		if (path.isEmpty() || path.endsWith("/"))
			path += "index.html";
		file = new File(getParentPath(), path);
		log.debug(file);
		return file;
	}

	private String getParentPath() {
		return new File(FilesOnlyResponder.class.getResource("/web/testPage.html").getFile()).getParent();
	}

	@Override
	void writePage(HttpRequest httpRequest) {
		if (!file.exists() || HEAD.equals(httpRequest.getMethod()))
			return;
		try {
			ByteBuffer bb = ByteBuffer.allocate(1024);
			FileChannel fc = FileChannel.open(file.toPath());
			for (int len; (len = fc.read(bb)) != -1; ) {
				outputStream.write(bb.array(), 0, len);
				bb.clear();
			}
		} catch (IOException e) {
			log.error(e, e);
		}
	}
}
