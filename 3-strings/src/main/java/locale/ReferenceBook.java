package locale;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ReferenceBook {

	// Отсутствуют различные обработки исключительных ситуаций при вводе как с клавиатуры, так из из ресурсов.
	public static void main(String args[]) {
		System.out.printf("Choose your language (en/ru):%nВыберите язык (en/ru):%n");
		Scanner scanner = new Scanner(System.in);
		String lang = "";
		while (!"ru".equalsIgnoreCase(lang) && !"en".equalsIgnoreCase(lang))
			lang = scanner.nextLine();
		Locale locale = Locale.forLanguageTag(lang); //TODO не работает
		ResourceBundle myResources = ResourceBundle.getBundle("questions", locale);

		String title = myResources.getString("title");
		System.out.println(title);

		int questionsCount = Integer.parseInt(myResources.getString("questionsCount"));

		for (int i = 1; i <= questionsCount; i++) {
			String question = myResources.getString("question" + i);
			System.out.println(i + ") " + question);
		}

		for (int input = 1; input > 0 && input <= questionsCount; ) {
			input = scanner.nextInt();
			String question = myResources.getString("reply" + input);
			System.out.println(myResources.getString("reply"));
			System.out.println(input + ") " + question);
		}
	}
}
