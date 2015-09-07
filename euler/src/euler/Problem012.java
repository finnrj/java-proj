package euler;

import java.util.ArrayList;
import java.util.List;

import euler.util.Utils;

public class Problem012 {
	// triangle number with more than 500 factors

	public static void main(String[] args) {
		List<Integer> factors = new ArrayList<Integer>();
		int counter = 0;
		int sum = 0;
		int max = 0;
		while (factors.size() <= 500) {
			counter++;
			sum += counter;
			factors = Utils.factorize(sum);
			if (factors.size() > max) {
				max = factors.size();
				System.out.println("new max: " + max);
			}
		}
		System.out.println(sum + ", " + factors);
	}
}
