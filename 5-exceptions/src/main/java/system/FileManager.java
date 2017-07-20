package system;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Log4j2
public class FileManager {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		try (Scanner scanner = new Scanner(System.in)) { // при закрытии закроет входной поток программе.
			StringBuilder path = new StringBuilder(".");
			for (String input = ""; !input.equals("exit"); ) {
				File currentDir = new File(path.toString());
				String filesAndDirs[] = currentDir.list();
				System.out.println("Текущий каталог: " + currentDir.getAbsolutePath());
				System.out.println("Файлы/директории:");
				if (filesAndDirs != null)
					for (String file : filesAndDirs)
						System.out.println(file);
				System.out.println("Введите файл или директорию для чтения:");
				input = scanner.nextLine();
				switch (input) {
					case "create":
						createTxtFile(scanner, path.toString());
						break;
					case "delete":
						deleteTxtFile(scanner, path.toString());
						break;
					case "add":
						addToTxtFile(scanner, path.toString());
						break;
					case "..":
						path.delete(path.lastIndexOf("/"), path.length());
						break;
					default:
						path.append("/").append(input);
				}
			}
		}
	}

	private static void createTxtFile(Scanner scanner, String path) throws IOException {
		System.out.println("Введите название создаваемого файла:");
		String fileName = scanner.nextLine();
		if (new File(path + "/" + fileName + ".txt").createNewFile())
			System.out.println("Файл успешно создан.");
		else
			System.out.println("Не удалось создать файл.");
	}

	private static void deleteTxtFile(Scanner scanner, String path) {
		System.out.println("Введите название удаляемого файла:");
		String fileName = scanner.nextLine();
		if (new File(path + "/" + fileName + ".txt").delete())
			System.out.println("Файл успешно удалён.");
		else
			System.out.println("Не удалось удалить файл.");
	}

	private static void addToTxtFile(Scanner scanner, String path) {
		System.out.println("Введите название редактируемого файла:");
		String fileName = scanner.nextLine();
		File file = new File(path + "/" + fileName + ".txt");
		if (!file.exists()) {
			System.out.println("Такого файла не существует.");
			return;
		}
		System.out.println("Введите добавляемый текст:");
		String text = scanner.nextLine();
		appendToFile(file, text);
	}

	private static void appendToFile(File file, String text) {
		try (FileWriter fw = new FileWriter(file)) {
			fw.append(text);
			System.out.println("Текст успешно добавлен в файл.");
		} catch (IOException e) {
			log.error("Writing to file failed!", e);
		}
	}
}
