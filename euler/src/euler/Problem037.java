package euler;

import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import euler.util.Utils;

public class Problem037 {

	private static LongStream truncations(String target) {
		Builder<String> builder = Stream.builder();
		for (int i = 1; i < target.length(); i++) {
			builder.add(target.substring(i)).add(
					target.substring(0, target.length() - i));
		}
		return builder.build().mapToLong(Long::valueOf);
	}

	private static boolean onlyOdds(String target) {
		return Stream.of("02468".split("")).allMatch(d -> !target.contains(d));
	}

	public static void main(String[] args) {
		System.out.println(Utils
				.getPrimes(
						p -> p > 20
								&& (p.toString().endsWith("3") || p.toString().endsWith("7"))
								&& (p == 23 || onlyOdds(p.toString()))
								&& truncations(p.toString()).allMatch(tr -> Utils.isPrime(tr)))
				.limit(11).peek(System.out::println).reduce(Long::sum).get());
	}
}
