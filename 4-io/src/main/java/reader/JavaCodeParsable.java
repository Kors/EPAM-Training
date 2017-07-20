package reader;

import java.util.HashMap;
import java.util.Map;

public interface JavaCodeParsable {

	String[] KEYWORDS = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue",
			"default", "double", "do", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements",
			"import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public",
			"return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
			"transient", "try", "void", "volatile", "while"};

	default Map<String, Integer> parseKeywords(String text) {
		Map<String, Integer> map = new HashMap<>();
		for (String keyword : KEYWORDS) {
			int start = 0;
			int counter = 0;
			while ((start = text.indexOf(keyword, start) + 1) >= 1) // строго говоря такой подсчёт не корректен.
				counter++;
			if (counter > 0)
				map.put(keyword, counter);
		}
		return map;
	}
}
