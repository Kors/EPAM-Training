package system;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Log4j2
public class FileManager {
	// ������� ��� �������� ����� (exit,create,delete,add) �� ����� �������� ���������� ������ � ����������.

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) { // ��� �������� ������� ������� ����� ���������.
			StringBuilder path = new StringBuilder(".");
			for (String input = ""; !input.equals("exit"); ) {
				input = getNewCmd(scanner, path.toString());
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
						int index = path.lastIndexOf("/");
						path.delete(index > 0 ? index : 0, path.length());
						break;
					default:
						if (path.length() > 0)
							path.append("/");
						path.append(input);
				}
			}
		}
	}

	private static String getNewCmd(Scanner scanner, String path) {
		File currentDir = new File(path);
		String filesAndDirs[] = currentDir.list();
		System.out.println("������� �������: " + currentDir.getAbsolutePath());
		System.out.println("�����/����������:");
		if (filesAndDirs != null)
			for (String file : filesAndDirs)
				System.out.println(file);
		System.out.println("������� ���� ��� ���������� ��� ������ ��� ������� (exit,create,delete,add):");
		return scanner.nextLine();
	}

	private static void createTxtFile(Scanner scanner, String path) {
		System.out.println("������� �������� ������������ �����:");
		String fileName = scanner.nextLine();
		File file = new File(path + "/" + fileName + ".txt");
		tryToCreate(file);
	}

	private static void tryToCreate(File file) {
		try {
			System.out.println(file.createNewFile() ? "���� ������� ������." : "�� ������� ������� ����.");
		} catch (IOException e) {
			log.error("File creation failed!", e);
		}

	}

	private static void deleteTxtFile(Scanner scanner, String path) {
		System.out.println("������� �������� ���������� �����:");
		String fileName = scanner.nextLine();
		if (new File(path + "/" + fileName + ".txt").delete())
			System.out.println("���� ������� �����.");
		else
			System.out.println("�� ������� ������� ����.");
	}

	private static void addToTxtFile(Scanner scanner, String path) {
		System.out.println("������� �������� �������������� �����:");
		String fileName = scanner.nextLine();
		File file = new File(path + "/" + fileName + ".txt");
		if (!file.exists()) {
			System.out.println("������ ����� �� ����������.");
			return;
		}
		System.out.println("������� ����������� �����:");
		String text = scanner.nextLine();
		appendToFile(file, text);
	}

	private static void appendToFile(File file, String text) {
		try (FileWriter fw = new FileWriter(file)) {
			fw.append(text);
			System.out.println("����� ������� �������� � ����.");
		} catch (IOException e) {
			log.error("Writing into file failed!", e);
		}
	}
}
