package system;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

@Log4j2
public class PropsReader {

	private Properties props = new Properties();

	public static void main(String[] args) {
		new PropsReader().run();
	}

	private void run() {
		try (Scanner scanner = new Scanner(System.in)) { // ��� �������� ������� ������� ����� ���������.
			tryLoadProps(scanner);
			for (String key = ""; !"exit".equals(key); ) {
				System.out.println("������� �������� property, ������� ���������� ���������:");
				key = scanner.nextLine();
				if ("all".equals(key))
					readAllProps();
				else
					System.out.println(readProperty(key));
			}
		}
	}

	private void tryLoadProps(Scanner scanner) {
		while (props.isEmpty()) {
			System.out.println("������ ���� ��� ������ properties:");
			String propsPath = scanner.nextLine();
			try {
				loadProps(propsPath);
			} catch (IOException e) {
				log.error(() -> "Reading properties from '" + propsPath + "' failed.", e);
			}
		}
	}

	private void loadProps(String propsPath) throws IOException {
		props.load(new FileInputStream(propsPath));
	}

	private void readAllProps() {
		Set<String> names = props.stringPropertyNames();
		for (String key : names)
			System.out.println(key + " = " + readProperty(key));
	}


	private String readProperty(String key) {
		return props.getProperty(key);
	}
}
