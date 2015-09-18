package euler;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem026 {

	public static void main(String[] args) throws IOException {

		for (int i = 1; i < 1000; i++) {
			BigDecimal divide = BigDecimal.ONE.divide(new BigDecimal(i), 10000,
					RoundingMode.UP);
			if (1000 % i == 0) {
				continue;
			}
			int multiplicator = (i < 10) ? 10 : (i < 100) ? 100 : 1000;
			BigDecimal result = divide.multiply(new BigDecimal(multiplicator));
			String str = result.toString();
			int startIndex = 2;
			while (str.charAt(startIndex) == '0') {
				startIndex++;
			}
			for (int sequenceLength = 1; sequenceLength < 1000; sequenceLength++) {
				if (startIndex + 2 * sequenceLength > str.length()) {
					System.out.println(String.format(
							"%03d, no period smaller than : %03d", i, sequenceLength));
					break;
				}

				if (str.subSequence(startIndex, startIndex + sequenceLength).equals(
						str.subSequence(startIndex + sequenceLength, startIndex + 2
								* sequenceLength))) {
					System.out.println(String.format("%d, period: %d%s", i,
							sequenceLength, ""
					// (Utils.isPrime(i) ? ", is prime" : ""
							));
					break;
				}
			}
		}

		Stream<AbstractMap.SimpleEntry<Integer, String>> fractions = IntStream
				.range(1, 1000)
				.filter(i -> 1000 % i != 0)
				.mapToObj(
						i -> new AbstractMap.SimpleEntry<Integer, String>(i, BigDecimal.ONE
								.divide(new BigDecimal(i), 10000, RoundingMode.UP)
								.multiply(
										new BigDecimal((i < 10) ? 10 : (i < 100) ? 100 : 1000))
								.toString().substring(4)));
		fractions
				.map(
						e -> {
							int foundPeriod = IntStream
									.rangeClosed(1, 1000)
									.filter(
											i -> e.getValue().subSequence(0, i)
													.equals(e.getValue().subSequence(i, 2 * i)))
									.findAny().orElse(-1);
							return String.format("%d, period length: %d", e.getKey(),
									foundPeriod);
						}).forEach(System.out::println);
		;
	}
}
