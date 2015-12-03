package euler;

import java.math.BigInteger;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.stream.Stream;

public class Problem025 {

	private static SimpleImmutableEntry<Integer, SimpleImmutableEntry<BigInteger, BigInteger>> createElement(
			Integer index, SimpleImmutableEntry<BigInteger, BigInteger> previous) {
		return new SimpleImmutableEntry<Integer, SimpleImmutableEntry<BigInteger, BigInteger>>(
				index + 1, new SimpleImmutableEntry<BigInteger, BigInteger>(
						previous.getValue(), previous.getKey().add(previous.getValue())));
	}

	public static void main(String[] args) {
		SimpleImmutableEntry<Integer, SimpleImmutableEntry<BigInteger, BigInteger>> first = //
		new SimpleImmutableEntry<Integer, SimpleImmutableEntry<BigInteger, BigInteger>>(
				1, new SimpleImmutableEntry<BigInteger, BigInteger>(BigInteger.ZERO,
						BigInteger.ONE));
		Stream<SimpleImmutableEntry<Integer, SimpleImmutableEntry<BigInteger, BigInteger>>> fibos = Stream
				.<SimpleImmutableEntry<Integer, SimpleImmutableEntry<BigInteger, BigInteger>>> iterate(
						first, e -> createElement(e.getKey(), e.getValue()));
		SimpleImmutableEntry<Integer, SimpleImmutableEntry<BigInteger, BigInteger>> searchedForElement = fibos
				.filter(e -> String.valueOf(e.getValue().getValue()).length() >= 1000)
				.findFirst().get();
		System.out.println(searchedForElement.getKey() + " = "
				+ searchedForElement.getValue().getValue());
	}
}
