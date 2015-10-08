package euler;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import euler.util.Utils;

public class Problem049 {

	public static void main(String[] args) throws IOException {
		Stream<Long> fourDigitPrimes = Utils
				.getPrimes(p -> p.toString().length() == 4);
		System.out.println(Arrays.toString("1487".split("")));
	}
}
