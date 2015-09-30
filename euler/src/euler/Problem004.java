package euler;

import java.time.Duration;
import java.time.Instant;

import euler.util.Utils;

public class Problem004 {

	public static void main(String[] args) {
		Instant start = Instant.now();
		solve();
		System.out.println("solve "
				+ Duration.between(start, start = Instant.now()));
	}

	private static void solve() {
		int max = 0;
		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {
				int product = i * j;
				if (Utils.isPalindrome(product) && product > max) {
					max = product;
					System.out.println(String.format("%d * %d = %d", i, j, product));
				}
			}
		}
	}
}
