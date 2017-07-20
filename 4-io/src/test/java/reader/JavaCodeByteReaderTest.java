package reader;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

class JavaCodeByteReaderTest {

	private JavaCodeByteReader jcr = new JavaCodeByteReader();

	@Test
	void writeTest() throws IOException, URISyntaxException {
		String text = jcr.readFile();
		Map<String, Integer> keywords = jcr.parseKeywords(text);
		String file = "result.txt";
		jcr.writeToFile(keywords, file);

		Path correctFile = Paths.get(JavaCodeByteReaderTest.class.getResource("/correctResult.txt").toURI());
		assertThat(Files.readAllBytes(Paths.get(file)),
				IsEqual.equalTo(Files.readAllBytes(correctFile)));
	}

}