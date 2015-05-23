package euler;

import java.io.IOException;

public class Problem010 {

	public static void main(String[] args) throws IOException {
		System.out.println(Utils.getPrimes(p -> p < 2_000_000)
				.peek(p -> System.out.println(p + " ")).sum()); // 1179908154
	}
}
