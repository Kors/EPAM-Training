package rewriter;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TextRewriterTest {

	@Test
	void testEquality() throws IOException {
		String utf8FileName = TextRewriter.class.getResource("/utf-8_cyrillic_text.txt").getFile();
		String utf8FileText = TextRewriter.readFile(utf8FileName, "UTF-8");
		String utf16FileName = "utf-16_cyrillic_text.txt";
		TextRewriter.writeToFile(utf8FileText, utf16FileName);
		String utf16FileText = TextRewriter.readFile(utf16FileName, "UTF-16");

		assertThat(utf16FileText, IsEqual.equalTo(utf8FileText));
	}

}