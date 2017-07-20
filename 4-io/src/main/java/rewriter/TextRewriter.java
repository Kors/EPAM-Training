package rewriter;

import java.io.*;

public class TextRewriter {

	public static void main(String[] args) throws IOException {
		String text = readFile(TextRewriter.class.getResource("/utf-8_cyrillic_text.txt").getFile(), "UTF-8");
		String fileName = "utf-16_cyrillic_text.txt";
		System.out.println("write to file:" + new File(fileName).getAbsolutePath());
		writeToFile(text, fileName);
	}

	static String readFile(String fileName, String encoding) throws IOException {
		StringBuilder sb = new StringBuilder();
		String separator = System.lineSeparator();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(fileName), encoding))) {
			br.lines().forEach(s -> sb.append(s).append(separator));
		}
		return sb.toString();
	}

	static void writeToFile(String text, String fileName) throws IOException {
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
			bos.write(text.getBytes("UTF-16"));
		}
	}
}
