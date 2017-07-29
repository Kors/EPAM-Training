package server;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;

class ContentTypeParserTest {

	@DisplayName("Get matched MIME Type")
	@ParameterizedTest
	@CsvSource({"index.html, text/html", "page.txt, text/plain", "favicon.ico, image/x-icon", "image.jpg, image/jpeg",
			"flower.png, image/png", "some.unknown, application/octet-stream", "noExtension, application/octet-stream",
			"image.JPG, image/jpeg"})
	void getMimeTypeTest(String fileName, String mimeType) {
		assertThat(ContentTypeParser.getMimeType(fileName), Is.is(mimeType));
	}

}