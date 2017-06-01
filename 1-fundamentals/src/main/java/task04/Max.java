package task04;

import java.util.Random;

public class Max {

	private static double[] numbers;

	public static void main(String[] args) {
		if (args.length > 0) {
			readArgs(args);
			assertEvenNumbers();
		} else
			createRandomArray();
		printArray();
		System.out.printf("Максимальная сумма (Am+An-m) = %s%n", maxSum());
	}

	private static void readArgs(String[] args) {
		numbers = new double[args.length];
		for (int i = 0; i < args.length; i++) {
			numbers[i] = readDouble(args[i]);
		}
	}

	private static double readDouble(String arg) {
		return Double.parseDouble(arg);
	}

	private static void assertEvenNumbers() {
		if (numbers.length % 2 != 0)
			throw new RuntimeException("Необходимо ввести чётное количество аргументов для создания массива.");
	}

	private static void createRandomArray() {
		Random rand = new Random();
		int length = (2 + rand.nextInt(9)) * 2;
		numbers = new double[length];
		for (int i = 0; i < length; i++)
			numbers[i] = rand.nextInt(100);
	}

	private static double maxSum() {
		double max = Double.MIN_VALUE;
		int length = numbers.length;
		for (int i = 0; i < length / 2; i++) {
			double sum = numbers[i] + numbers[length - 1 - i];
			if (sum > max)
				max = sum;
		}
		return max;
	}

	private static void printArray() {
		System.out.print("Массив: [");
		for (double d : numbers)
			System.out.printf("%s; ", d);
		System.out.println("\b\b]");
	}
}
