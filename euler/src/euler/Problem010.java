package euler;

import java.io.IOException;

import euler.util.Utils;

public class Problem010 {

	public static void main(String[] args) throws IOException {
		System.out.println(Utils.getPrimes(p -> p < 2_000_000)
				.peek(p -> System.out.println(p + " ")).mapToLong(i -> i).sum()); // 142913828922
	}
}
