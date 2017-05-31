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
			if (varName.equals("h=")) {
				h = readDouble(arg);
			} else if (varName.equals("a=")) {
				a = readDouble(arg);
			} else if (varName.equals("b=")) {
				b = readDouble(arg);
			}
		}
	}

	private static double readDouble(String arg) {
		return Double.parseDouble(arg.substring(2));
	}

	private static void assertValues() {
		if (a > b)
			throw new RuntimeException("Значение начала интервала a=" + a + " больше значения конца b=" + b + ". " +
					"Необходимо задать корректный интервал.");
		if (h <= 0)
			throw new RuntimeException("Значение шага h=" + h + " отрицательное!" +
					"Необходимо задать положительное значение(больше 0).");
	}

	private static void printFunctionValues() {
		System.out.println("x  ->  f(x)");
		for (double x = a; x <= b; x += h) {
			System.out.println(x + "  ->  " + function(x));
		}
	}

	private static double function(double x) {
		return Math.tan(2 * x) - 3;
	}
}
