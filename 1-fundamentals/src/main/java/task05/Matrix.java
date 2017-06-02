package task05;

public class Matrix {
	private static int n = 7;

	public static void main(String... args) {
		readArgs(args);
		printMatrix(createMatrix());
	}

	private static void readArgs(String[] args) {
		for (String arg : args)
			if (arg.startsWith("n="))
				n = readInt(arg);
	}

	private static int readInt(String arg) {
		return Integer.parseInt(arg.substring(2));
	}

	private static int[][] createMatrix() {
		int[][] matrix = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == j || i == n - 1 - j)
					matrix[i][j] = 1;
		return matrix;
	}

	private static void printMatrix(int[][] matrix) {
		for (int i = 0; i < n; i++) {
			System.out.print("(");
			for (int j = 0; j < n; j++)
				System.out.printf("%d ", matrix[i][j]);
			System.out.println("\b)");
		}
	}
}
