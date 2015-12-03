package euler;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem039 {
	private static class Triple implements Comparable<Triple> {
		private Integer a;
		private Integer b;
		private Integer n;

		public Triple(Integer a, Integer b, Integer n) {
			this.a = a;
			this.b = b;
			this.n = n;
		}

		public Integer getSum() {
			return a + b + n;
		}

		@Override
		public String toString() {
			return String.format("%d² + %d² = %d²", a, b, n);
		}

		@Override
		public int compareTo(Triple other) {
			return Long.compare(n, other.n);
		}
	}

	public static void main(String[] args) {
		Stream<Triple> pythagorases = IntStream
				.rangeClosed(3, 332)
				.boxed()
				.flatMap(
						a -> {
							return IntStream
									.rangeClosed(a + 1, 400)
									.boxed()
									.filter(b -> a * b % 12 == 0)
									.map(b -> Math.sqrt(a * a + b * b))
									.filter(sqrt -> sqrt == sqrt.intValue())
									.mapToInt(Double::intValue)
									.mapToObj(
											n -> new Triple(a, (int) Math.sqrt(n * n - a * a), n));
						});
		Comparator<Map.Entry<Integer, Long>> byValueDesc = (e1, e2) -> e2
				.getValue().compareTo(e1.getValue());
		Instant start = Instant.now();
		pythagorases
				.collect(Collectors.groupingBy(Triple::getSum, Collectors.counting()))
				.entrySet().stream().sorted(byValueDesc).limit(1);
		System.out.println(Duration.between(start, Instant.now()));
	}
}
