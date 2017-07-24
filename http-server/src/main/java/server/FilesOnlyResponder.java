package server;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

@Log4j2
class FilesOnlyResponder extends SocketProcessor {

	FilesOnlyResponder(Socket socket) throws IOException {
		super(socket);
	}

	@Override
	void writePage(HttpRequest httpRequest) {
		try {
			ByteBuffer bb = ByteBuffer.allocate(1024);
			FileChannel fc = FileChannel.open(Paths.get(SocketProcessor.class.getResource("/testPage.html").toURI()));
			for (int len; (len = fc.read(bb)) != -1; ) {
				outputStream.write(bb.array(), 0, len);
				bb.clear();
			}
		} catch (IOException | URISyntaxException e) {
			log.error(e);
		}
	}
}
