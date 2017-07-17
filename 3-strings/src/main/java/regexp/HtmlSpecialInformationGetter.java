package regexp;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j2
public class HtmlSpecialInformationGetter {

	static final String picRefRegExp = "[–р]ис((\\.)|(унке)) (\\d+)"; // TODO ссылки типа (–ис. 22 и 24) не учитывают вторую цифру
	private static final String sentenceWithLinkRegExp = "([ј-яA-Z]([^.]+?))?([^.]*?(" + picRefRegExp + "))+(([.!?])|([^.]*?[^с][.!?])|([^.]*?[^и].[.!?])|([^.]*?[^–р]..[.!?]))";

	List<String> picBySent = Collections.emptyList(); // дл€ дебага (проще пон€ть где ошибка в регул€рке)
	String text;

	HtmlSpecialInformationGetter() {
		text = getText();
	}

	public static void main(String... args) throws IOException {
		HtmlSpecialInformationGetter informationGetter = new HtmlSpecialInformationGetter();
		show("—сылки автора расположены" + (informationGetter.areLinksOrdered() ? "" : " не") + " по пор€дку");
		informationGetter.showSentencesWithLinks();
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

	void showSentencesWithLinks() {
		text = text.substring(text.indexOf("<br style=\"clear:both;\">"), text.indexOf("</body>")); // отрезаем заведомо лишнее
		List<String> separatedText = Arrays.stream(text.split("<div>"))
				.map(s -> s.replaceAll("<.*?>", "")) // вырезаем все теги
				.map(s -> s.replaceAll("^|$", ""))
				.map(s -> s.replaceAll("\r", ""))
				.map(s -> s.replaceAll("\n", ""))
				.map(s -> s.replaceAll("&nbsp;", "")) // вырезаем спецсимволы
				.collect(Collectors.toList());
//		show("separatedText:" + separatedText.size());
		List<String> sentencesWithPicRef = new ArrayList<>(200);
		Pattern sentencePattern = Pattern.compile(sentenceWithLinkRegExp);
		for (String s : separatedText) {
			Matcher sentenceMatcher = sentencePattern.matcher(s);
			while (sentenceMatcher.find())
				sentencesWithPicRef.add(sentenceMatcher.group(0));
		}
//		show("sentencesWithPicRef:" + sentencesWithPicRef.size());

		int i = 0;
		Pattern p = Pattern.compile(picRefRegExp);
		picBySent = new ArrayList<>();
		for (String s : sentencesWithPicRef) {
			Matcher m = p.matcher(s);
			show(s);
			while (m.find()) {
				picBySent.add(m.group(0));
				i++;
			}
		}
//		show("" + i);
		assert i == 186; // проверка что этим способом не упустили ни одной ссылки
	}

	@SneakyThrows
	private static String getText() {
		File html = new File(HtmlSpecialInformationGetter.class.getResource("/Information.html").getFile());
		show("Html path: " + html.getAbsolutePath());
		try (BufferedReader f = new BufferedReader(new FileReader(html))) {
			return f.lines().collect(Collectors.joining()); // собираем всЄ в строку. (считаем что файл достаточно мал дл€ такой возможности.)
		}
	}

	private static void show(String s) {
		log.info(s);
	}
}
