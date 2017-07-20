package reader;

import java.io.*;
import java.util.Map;

public class JavaCodeByteReader implements JavaCodeParsable {

	public static void main(String[] args) throws IOException {
		new JavaCodeByteReader().run();
	}

	private void run() throws IOException {
		String text = readFile();
		Map<String, Integer> keywordsInFile = parseKeywords(text);
		String fileName = JavaCodeByteReader.class.getResource("/CodeExample.java").getPath()
				.replaceFirst("/[^/]+$", "") + "/keywordsParsedByByteReader.txt";
		System.out.println("write to file:" + fileName);
		writeToFile(keywordsInFile, fileName);
	}

	String readFile() throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedInputStream bis = new BufferedInputStream(
				JavaCodeByteReader.class.getResourceAsStream("/CodeExample.java"))) {
			while (bis.available() > 0)
				sb.append((char) bis.read()); // кириллица таким способом не поддерживается, но нам в данной задаче это и не надо
		}
		return sb.toString();
	}

	void writeToFile(Map<String, Integer> keywordsInFile, String fileName) throws IOException {
		byte[] separator1 = " ".getBytes();
		byte[] separator2 = "\n".getBytes();
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
			for (Map.Entry<String, Integer> entry : keywordsInFile.entrySet()) {
				bos.write(entry.getKey().getBytes());
				bos.write(separator1);
				bos.write(entry.getValue().toString().getBytes());
				bos.write(separator2);
			}
		}
	}
}
