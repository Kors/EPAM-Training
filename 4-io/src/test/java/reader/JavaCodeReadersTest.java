package reader;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JavaCodeReadersTest {

	private JavaCodeByteReader bcr = new JavaCodeByteReader();
	private JavaCodeCharReader ccr = new JavaCodeCharReader();

	@Test
	void parseEqualityTest() throws IOException {
		String byteText = bcr.readFile();
		Map<String, Integer> byteKeywords = bcr.parseKeywords(byteText);
		String charText = ccr.readFile();
		Map<String, Integer> charKeywords = ccr.parseKeywords(charText);

		assertThat(byteKeywords, IsEqual.equalTo(charKeywords));
	}

}