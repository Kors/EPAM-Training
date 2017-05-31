package task02;

import java.util.Random;

public class Finder {
	private static int n = 778;// считаем  что неизвестное по задаче условие "М" это "больше некоторого числа n"

	public static void main(String... args) {
		int[] array = createRandomArray();
		int index = getFirstMatched(array);
		printInfo(array, index);
		printTillIndex(array, index);
	}

	private static int[] createRandomArray() {
		Random rand = new Random();
		int[] arr = new int[rand.nextInt(10) + 5];
		for (int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(1000);
		return arr;
	}

	private static int getFirstMatched(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i] > n)
				return i;
		return -1;
	}

	private static void printInfo(int[] array, int index) {
		System.out.println("Массив: ");
		for (int val : array)
			System.out.print(val + "; ");
		System.out.println();
		System.out.println("Условие: число больше чем " + n);
		if (index < 0)
			throw new RuntimeException("Условие не выполнено.");
		System.out.println("Условие выполнено для элемента №" + index);
	}

	private static void printTillIndex(int[] array, int index) {
		System.out.println("Элементы массива до " + index + ":");
		for (int i = 0; i < array.length && i <= index; i++)
			System.out.println(array[i]);
	}
}
