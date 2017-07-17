package regexp;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j2
public class HtmlSpecialInformationGetter {

	static final String picRefRegExp = "[Рр]ис((\\.)|(унке)) (\\d+)";
	static final String sentenceRegExp = "([^(]|^)([А-ЯA-Z].+?[^А-ЯA-Z][.!?])(([<$])|( [^\\d]))";

	String text;

	HtmlSpecialInformationGetter() {
		text = getText();
	}

	public static void main(String[] args) throws IOException {
		HtmlSpecialInformationGetter informationGetter = new HtmlSpecialInformationGetter();
		log.info(() -> "Ссылки автора расположены" + (informationGetter.areLinksOrdered() ? "" : " не") + " по порядку");
		showSentencesWithLinks();
	}

	boolean areLinksOrdered() {
		Pattern p = Pattern.compile(picRefRegExp);
		Matcher ma = p.matcher(text);
		int lastNumber = 0;
		while (ma.find()) {
			int picNumber = Integer.parseInt(ma.group(4));
			if (picNumber < lastNumber)
				return false;
			lastNumber = picNumber;
		}
		return true;
	}

	private static void showSentencesWithLinks() {
	}

	@SneakyThrows
	private static String getText() {
		File html = new File(HtmlSpecialInformationGetter.class.getResource("/Information.html").getFile());
		log.info("Html path: " + html.getAbsolutePath());
		try (BufferedReader f = new BufferedReader(new FileReader(html))) {
			return f.lines().collect(Collectors.joining()); // собираем всё в строку. (считаем что файл достаточно мал для такой возможности.)
		}
	}
}
