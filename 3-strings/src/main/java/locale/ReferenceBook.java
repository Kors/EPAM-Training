package locale;

import java.util.*;

import static java.util.Arrays.asList;

public class ReferenceBook {
	private static Set<String> encodings = new HashSet<>();

	static {
		encodings.addAll(asList("", "ru", "en", "ru-RU", "en-GB"));
	}

	// Отсутствуют различные обработки исключительных ситуаций при вводе как с клавиатуры, так из из ресурсов.
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String lang = null;
		String enc = Arrays.toString(encodings.toArray(new String[5]));
		while (!encodings.contains(lang)) {
			System.out.printf("Choose your language %s:%nВыберите язык %s:%n", enc, enc);
			lang = scanner.nextLine();
		}
		Locale locale = Locale.forLanguageTag(lang);
		ResourceBundle myResources = ResourceBundle.getBundle("questions", locale);

		String usingLocale = myResources.getLocale().getDisplayName();
		System.out.println("Using locale: " + usingLocale);

		String title = myResources.getString("title");
		System.out.println(title);

		int questionsCount = Integer.parseInt(myResources.getString("questionsCount"));

		for (int i = 1; i <= questionsCount; i++) {
			String question = myResources.getString("question." + i);
			System.out.printf("%d) %s%n", i, question);
		}

		for (int input = scanner.nextInt();
		     input > 0 && input <= questionsCount;
		     input = scanner.nextInt()) {
			String question = myResources.getString("reply." + input);
			System.out.printf("%s%d) %s%n", myResources.getString("reply"), input, question);
		}
	}
}
