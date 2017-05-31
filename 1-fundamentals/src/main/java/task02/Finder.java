package task02;

public class Finder {
	private static double e = 0.1;

	public static void main(String... args) {
		readArgs(args);
		printSequenceTillE(e);
	}

	private static void readArgs(String[] args) {
		for (String val : args)
			if (val.startsWith("e="))
				e = Double.parseDouble(val.substring(2));
	}

	private static void printSequenceTillE(double e) {
		for (int i = 1; i < Integer.MAX_VALUE; i++) {
			double a = 1.0 / ((i + 1) * (i + 1));
			System.out.print(a + "; ");
			if (a < e) {
				System.out.println("\n���������� ����� �������� ������ e=" + e + " ����� " + i +
						"\n�������� ����� �������� = " + a);
				return;
			}
		}
		System.out.println("���� ��������� " + Integer.MAX_VALUE + " ��������� ������������������, " +
				"�� �������� ����� ��� " + e + " �� �������.");
	}
}
