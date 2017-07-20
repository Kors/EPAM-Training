package reader;

import java.io.*;
import java.util.Map;

public class JavaCodeCharReader implements JavaCodeParsable {

	public static void main(String[] args) throws IOException {
		new JavaCodeCharReader().run();
	}

	private void run() throws IOException {
		String text = readFile();
		Map<String, Integer> keywordsInFile = parseKeywords(text);
		String fileName = "keywordsParsedByCharReader.txt";
		System.out.println("write to file:" + new File(fileName).getAbsolutePath());
		writeToFile(keywordsInFile, fileName);
	}

	String readFile() throws IOException {
		StringBuilder sb = new StringBuilder();
		String separator = System.lineSeparator();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				JavaCodeByteReader.class.getResourceAsStream("/CodeExample.java")))) {
			br.lines().forEach(s -> sb.append(s).append(separator));
		}
		return sb.toString();
	}

	void writeToFile(Map<String, Integer> keywordsInFile, String fileName) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
			for (Map.Entry<String, Integer> entry : keywordsInFile.entrySet()) {
				bw.write(entry.getKey());
				bw.write(" ");
				bw.write(entry.getValue().toString());
				bw.newLine();
			}
		}
	}
}