package euler;

public class Problem033 {

	public static void main(String[] args) {

		for (int b = 9; b > 1; b--) {
			for (int a = b - 1; a > 0; a--) {
				for (int x = 1; x < 10; x++) {
					if (a * (x * 10 + b) == b * (x * 10 + a)) {
						System.out.println(String.format("i) %d%d/%d%d = %d/%d", x, a, x,
								a, b));
					}
					if (a * (b * 10 + x) == b * (x * 10 + a)) {
						System.out.println(String.format("ii) %d%d/%d%d = %d/%d", x, a, b,
								x, a, b));
					}
					if (a * (x * 10 + b) == b * (a * 10 + x)) {
						System.out.println(String.format("ii) %d%d/%d%d = %d/%d", a, x, x,
								b, a, b));
					}
					if (a * (b * 10 + x) == b * (a * 10 + x)) {
						System.out.println(String.format("ii) %d%d/%d%d = %d/%d", a, x, b,
								x, a, b));
					}
				}
			}
		}
	}
}
