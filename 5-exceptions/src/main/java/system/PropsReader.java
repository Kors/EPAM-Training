package system;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

@Log4j2
public class PropsReader {

	Properties props = new Properties();

	public static void main(String[] args) {
		new PropsReader().run();
	}

	private void run() {
		try (Scanner scanner = new Scanner(System.in)) { // при закрытии закроет входной поток программе.
			tryLoadProps(scanner);
			for (String key = ""; !"exit".equals(key); ) {
				System.out.println("Введите название property, которую необходимо прочитать:");
				key = scanner.nextLine();
				if ("all".equals(key))
					showAllProps();
				else
					System.out.println(readProperty(key));
			}
		}
	}

	private void tryLoadProps(Scanner scanner) {
		while (props.isEmpty()) {
			System.out.println("Ведите файл для чтения properties:");
			String propsPath = scanner.nextLine();
			loadProps(propsPath);
		}
	}

	void loadProps(String propsPath) {
		try {
			props.load(new FileInputStream(propsPath));
		} catch (IOException e) {
			log.error(() -> String.format("Reading properties from '%s' failed.", propsPath), e);
		}
	}

	private void showAllProps() {
		Set<String> names = props.stringPropertyNames();
		for (String key : names)
			System.out.printf("%s = %s%n", key, readProperty(key));
	}


	String readProperty(String key) {
		return props.getProperty(key);
	}
}
