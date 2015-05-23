package euler;

import java.util.ArrayList;
import java.util.List;

public class Problem012 {
	// triangle number with more than 500 factors

	private static List<Integer> factorize(Integer n) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				result.add(i);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		List<Integer> factors = new ArrayList<Integer>();
		int counter = 0;
		int sum = 0;
		int max = 0;
		while (factors.size() <= 500) {
			counter++;
			sum += counter;
			factors = factorize(sum);
			if (factors.size() > max) {
				max = factors.size();
				System.out.println("new max: " + max);
			}
		}
		System.out.println(sum + ", " + factors);
	}
}
