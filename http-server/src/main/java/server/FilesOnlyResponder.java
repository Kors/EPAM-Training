package server;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

@Log4j2
class FilesOnlyResponder extends SocketProcessor {

	private File f;
	private boolean writeBody = true;

	FilesOnlyResponder(Socket socket) throws IOException {
		super(socket);
	}

	@Override
	Map<String, String> getHeaderProps(HttpRequest httpRequest) {
		Map<String, String> m = new HashMap<>();
		m.put("contentType", "text/html");
		m.put("length", "0");
		if (httpRequest.getMethod() != HttpMethod.GET && httpRequest.getMethod() != HttpMethod.HEAD) {
			m.put("code", "501 Not Implemented");
			writeBody = false;
			return m;
		}
		if (httpRequest.getMethod() == HttpMethod.HEAD)
			writeBody = false;
		f = new File(new File(FilesOnlyResponder.class.getResource("/testPage.html").getFile()).getParent(), httpRequest.getPath());
		if (f.exists()) {
			m.put("code", "200 OK");
			m.put("length", String.valueOf(f.length()));
		} else {
			m.put("code", "404 Not Found");
			writeBody = false;
		}
		return m;
	}

	@Override
	void writePage(HttpRequest httpRequest) {
		if (!writeBody)
			return;
		try {
			ByteBuffer bb = ByteBuffer.allocate(1024);
			FileChannel fc = FileChannel.open(f.toPath());
			for (int len; (len = fc.read(bb)) != -1; ) {
				outputStream.write(bb.array(), 0, len);
				bb.clear();
			}
		} catch (IOException e) {
			log.error(e);
		}
	}
}
