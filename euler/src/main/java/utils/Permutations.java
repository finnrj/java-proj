package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Permutations {

	private Permutations() {
	}

	public static BigInteger factorialBI(int n) {
		if (n == 0) {
			return BigInteger.ONE;
		}
		return Stream.iterate(BigInteger.ONE, bi -> bi.add(BigInteger.ONE)).limit(n)
				.reduce(BigInteger.ONE, (b1, b2) -> b1.multiply(b2));
	}

	public static BigInteger combinationsBI(int from, int choose) {
		if (from < 0 || choose < 0 || from < choose) {
			throw new IllegalArgumentException(
					String.format("from: %d, choose: %d are out of range", from, choose));
		}
		BigInteger denominator = factorialBI(choose)
				.multiply(factorialBI(from - choose));
		return factorialBI(from).divide(denominator);
	}

	public static long factorial(int n) {
		if (n > 20 || n < 0)
			throw new IllegalArgumentException(n + " is out of range");
		return LongStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
	}

	public static <T> List<T> permutation(long no, List<T> items) {
		return permutationHelper(no,
				new LinkedList<>(Objects.requireNonNull(items)), new ArrayList<>());
	}

	public static <T> List<T> permutation(long no, Stream<T> items) {
		return permutationHelper(no,
				new LinkedList<>(
						Objects.requireNonNull(items.collect(Collectors.toList()))),
				new ArrayList<>());
	}

	private static <T> List<T> permutationHelper(long no, LinkedList<T> in,
			List<T> out) {
		if (in.isEmpty())
			return out;
		long subFactorial = factorial(in.size() - 1);
		out.add(in.remove((int) (no / subFactorial)));
		return permutationHelper((int) (no % subFactorial), in, out);
	}

	public static <T> Stream<Stream<T>> of(List<T> items) {
		return LongStream.range(0, factorial(items.size()))
				.mapToObj(no -> permutation(no, items).stream());
	}

	@SafeVarargs
	// Creating a List from an array is safe
	public static <T> Stream<Stream<T>> of(T... items) {
		return of(Arrays.asList(items));
	}

	public static void main(String[] args) {
		System.out.println(Permutations.combinationsBI(5,3));
	}

}