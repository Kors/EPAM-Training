package logger;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

class CrazyLoggerTest {

	private static CrazyLogger log = new CrazyLogger();
	private PrintStream out = System.out;
	private ByteOutputStream testStream = new ByteOutputStream();
	private PrintStream ps = new PrintStream(testStream);

	@BeforeEach
	void init() {
		log.write("Info note");
		log.write("Warn note");
		log.write("Error note");
		log.write("Another simple warn note");
	}

	@Test
	void findInLog() {
		System.setOut(ps);
		log.findAndShow("WARN");
		System.setOut(out);
		String result = new String(testStream.getBytes()).trim();
		assertThat(result, containsString("Warn note"));
		assertThat(result, containsString("Another simple warn note"));
	}

	@Test
	void noInfoInLog() {
		System.setOut(ps);
		log.findAndShow("dog");
		System.setOut(out);
		String result = new String(testStream.getBytes()).trim();
		assertThat(result, is(""));
	}

}