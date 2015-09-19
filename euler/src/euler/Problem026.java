package euler;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem026 {

	private static UnaryOperator<Integer> IDENTITY = UnaryOperator
			.<Integer> identity();

	private static class IntegerSequenceLength implements
			Comparable<IntegerSequenceLength> {
		private Integer integer;
		private Long sequenceLength;

		public IntegerSequenceLength(Integer integer, Long sequenceLength) {
			this.integer = integer;
			this.sequenceLength = sequenceLength;
		}

		@Override
		public int compareTo(IntegerSequenceLength other) {
			return Long.compare(this.sequenceLength, other.sequenceLength);
		}

		@Override
		public String toString() {
			return String.format("Integer: %d, period length: %d", integer,
					sequenceLength);
		}
	};

	private static void finalStreamSolutionWithHelperClass() {
		System.out.println(IntStream
				.range(1, 1000)
				.mapToObj(
						e -> {
							long sequenceLength = IntStream
									.iterate(1, n -> (n * 10) % e)
									.limit(2 * e)
									.boxed()
									.collect(
											Collectors.groupingBy(IDENTITY, Collectors.counting()))
									.entrySet().stream()
									.filter(moduloCount -> moduloCount.getValue() > 1 && moduloCount.getKey() != 0)
									.count();
							return new IntegerSequenceLength(e, sequenceLength);
						}).max(IntegerSequenceLength::compareTo).get());
	}

	private static void finalStreamSolution() {
		SimpleImmutableEntry<Integer, Long> result = IntStream
				.range(1, 1000)
				.mapToObj(
						e -> {
							long sequenceLength = IntStream
									.iterate(1, n -> (n * 10) % e)
									.limit(2 * e)
									.boxed()
									.collect(
											Collectors.groupingBy(UnaryOperator.<Integer> identity(),
													Collectors.counting())).entrySet().stream()
									.filter(m_c -> m_c.getValue() > 1 && m_c.getKey() != 0)
									.count();
							return new AbstractMap.SimpleImmutableEntry<Integer, Long>(e,
									sequenceLength);
						}).max((e1, e2) -> (int) (e1.getValue() - e2.getValue())).get();
		System.out.println(String.format("%d, period length: %d", result.getKey(),
				result.getValue()));
	}

	public static void main(String[] args) {
		finalStreamSolution();
		finalStreamSolutionWithHelperClass();
	}
}

// private static void iterativeAsStreamSolution() {
// Stream<AbstractMap.SimpleEntry<Integer, String>> fractions = IntStream
// .range(1, 1000)
// .filter(i -> 1000 % i != 0)
// .mapToObj(
// i -> new AbstractMap.SimpleEntry<Integer, String>(i, BigDecimal.ONE
// .divide(new BigDecimal(i), 10000, RoundingMode.UP)
// .multiply(
// new BigDecimal((i < 10) ? 10 : (i < 100) ? 100 : 1000))
// .toString().substring(4)));
// fractions
// .map(
// e -> {
// int foundPeriod = IntStream
// .rangeClosed(1, 1000)
// .filter(
// i -> e.getValue().subSequence(0, i)
// .equals(e.getValue().subSequence(i, 2 * i)))
// .findAny().orElse(-1);
// return String.format("%d, period length: %d", e.getKey(),
// foundPeriod);
// }).forEach(System.out::println);
// }
//
// private static void iterativeSolution() {
// for (int i = 1; i < 1000; i++) {
// BigDecimal divide = BigDecimal.ONE.divide(new BigDecimal(i), 10000,
// RoundingMode.UP);
// if (1000 % i == 0) {
// continue;
// }
// int multiplicator = (i < 10) ? 10 : (i < 100) ? 100 : 1000;
// BigDecimal result = divide.multiply(new BigDecimal(multiplicator));
// String str = result.toString();
// int startIndex = 2;
// while (str.charAt(startIndex) == '0') {
// startIndex++;
// }
// for (int sequenceLength = 1; sequenceLength < 1000; sequenceLength++) {
// if (startIndex + 2 * sequenceLength > str.length()) {
// System.out.println(String.format(
// "%03d, no period smaller than : %03d", i, sequenceLength));
// break;
// }
//
// if (str.subSequence(startIndex, startIndex + sequenceLength).equals(
// str.subSequence(startIndex + sequenceLength, startIndex + 2
// * sequenceLength))) {
// System.out.println(String.format("%d, period: %d%s", i,
// sequenceLength, ""
// // (Utils.isPrime(i) ? ", is prime" : ""
// ));
// break;
// }
// }
// }
// }

