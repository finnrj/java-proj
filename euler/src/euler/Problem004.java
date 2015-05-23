package euler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Problem004 {

	public static void main(String[] args) {
		int max = 0;
		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {
				int product = i * j;
				if (palindrome(product) && product > max) {
					max = product;
					System.out.println(String.format("%d * %d = %d", i, j, product));
				}
			}
		}
	}

	private static boolean palindrome(int product) {
		List<String> asList = Arrays.asList(String.valueOf(product).split(""));
		Collections.reverse(asList);
		return Integer.parseInt(String.join("", asList)) == product;
	}
}
