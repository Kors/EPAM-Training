package regexp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HtmlSpecialInformationGetter {

	public static void main(String[] args) throws IOException {
		File html = new File("3-strings/src/main/resources/Information.html");
		System.out.println("Path: " + html.getAbsolutePath());
		Pattern p = Pattern.compile("[Рр]ис((\\.)|(унке)) \\d+");
		try (BufferedReader f = new BufferedReader(new FileReader(html))) {
			List<String> l = f.lines().parallel()
					.filter((s) -> s.matches(".*[Рр]ис((\\.)|(унке)) \\d+.*"))
					.map(String::trim)
					.collect(Collectors.toList());
			System.out.println(l.size());
			for (String s : l) {
				Matcher m = p.matcher(s);
				if (m.find())
					System.out.println(m.group(0));
				else
					throw new RuntimeException("Несоответствие в строке! " + s);
			}
		}
	}
}
