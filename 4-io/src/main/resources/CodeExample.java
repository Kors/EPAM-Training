package locale;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

public class ReferenceBook {
	private static Set<String> encodings = new HashSet();

	public ReferenceBook() {
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String lang = null;

		for(String enc = Arrays.toString(encodings.toArray(new String[5])); !encodings.contains(lang); lang = scanner.nextLine()) {
			System.out.printf("Choose your language %s:%nВыберите язык %s:%n", new Object[]{enc, enc});
		}

		Locale locale = Locale.forLanguageTag(lang);
		ResourceBundle myResources = ResourceBundle.getBundle("questions", locale);
		String usingLocale = myResources.getLocale().getDisplayName();
		System.out.println("Using locale: " + usingLocale);
		String title = myResources.getString("title");
		System.out.println(title);
		int questionsCount = Integer.parseInt(myResources.getString("questionsCount"));

		int input;
		String question;
		for(input = 1; input <= questionsCount; ++input) {
			question = myResources.getString("question." + input);
			System.out.printf("%d) %s%n", new Object[]{Integer.valueOf(input), question});
		}

		for(input = scanner.nextInt(); input > 0 && input <= questionsCount; input = scanner.nextInt()) {
			question = myResources.getString("reply." + input);
			System.out.printf("%s%d) %s%n", new Object[]{myResources.getString("reply"), Integer.valueOf(input), question});
		}

	}

	static {
		encodings.addAll(Arrays.asList(new String[]{"", "ru", "en", "ru-RU", "en-GB"}));
	}
}
