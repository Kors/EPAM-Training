package map;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Log4j2
public class PropsReader {

	Map<String, String> props = new HashMap<>();

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
		props.putAll(getPropsFromFile(propsPath));
	}

	private Map<String, String> getPropsFromFile(String propsPath) {
		try (BufferedReader br = new BufferedReader(new FileReader(propsPath))) {
			Map<String, String> newProps = new HashMap<>();
			br.lines().forEach(
					(line) -> {
						int separatorIndex = line.indexOf("=");
						if (separatorIndex < 0)
							return;
						newProps.put(line.substring(0, separatorIndex),
								line.substring(separatorIndex + 1, line.length()));
					}
			);
			return newProps;
		} catch (IOException e) {
			log.error(() -> String.format("Reading properties from '%s' failed.", propsPath), e);
			return Collections.emptyMap();
		}
	}

	private void showAllProps() {
		Set<String> names = props.keySet();
		for (String key : names)
			System.out.printf("%s = %s%n", key, readProperty(key));
	}


	String readProperty(String key) {
		return props.get(key);
	}
}
