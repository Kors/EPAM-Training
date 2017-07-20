package rewriter;

import java.io.*;

public class TextRewriter {

	public static void main(String[] args) throws IOException {
		String text = readFile();
		String fileName = "utf-16_cyrillic_text.txt";
		System.out.println("write to file:" + new File(fileName).getAbsolutePath());
		writeToFile(text, fileName);
	}

	private static String readFile() throws IOException {
		StringBuilder sb = new StringBuilder();
		String separator = System.lineSeparator();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(TextRewriter.class.getResource("/utf-8_cyrillic_text.txt").getFile()), "UTF8"))) {
			br.lines().forEach(s -> sb.append(s).append(separator));
		}
		return sb.toString();
	}

	private static void writeToFile(String text, String fileName) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF16"))) {
			bw.write(text);
		}
	}
}
