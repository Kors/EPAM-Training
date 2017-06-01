package task03;

public class Function {

	private static double h = 0.05;
	private static double a = 0;
	private static double b = 2;

	public static void main(String... args) {
		readArgs(args);
		assertValues();
		printFunctionValues();
	}

	private static void readArgs(String[] args) {
		for (String arg : args) {
			String varName = arg.substring(0, 2);
			switch (varName) {
				case "h=":
					h = readDouble(arg);
					break;
				case "a=":
					a = readDouble(arg);
					break;
				case "b=":
					b = readDouble(arg);
					break;
			}
		}
	}

	private static double readDouble(String arg) {
		return Double.parseDouble(arg.substring(2));
	}

	private static void assertValues() {
		if (a > b)
			throw new RuntimeException(String.format("�������� ������ ��������� a=%.3f ������ �������� ����� b=%.3f. " +
					"���������� ������ ���������� ��������.", a, b));
		if (h <= 0)
			throw new RuntimeException(String.format("�������� ���� h=%.5f �������������!" +
					"���������� ������ ������������� ��������(������ 0).", h));
	}

	private static void printFunctionValues() {
		System.out.println("x    ->    f(x)");
		for (double x = a; x <= b; x += h) {
			System.out.printf("%.3f | %.3f%n", x, function(x));
		}
	}

	private static double function(double x) {
		return Math.tan(2 * x) - 3;
	}
}
