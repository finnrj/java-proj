package euler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem042 {

	public static Long getCharsValue(String word) {
		return word
				.chars()
				.mapToLong(
						ch -> Character.getNumericValue(ch)
								- Character.getNumericValue('A') + 1).sum();
	}

	public static void main(String[] args) throws IOException {
		List<String> words = new ArrayList<String>(Stream
				.of(Files.lines(Paths.get(".", "docs/p042_words.txt"))
						.collect(Collectors.joining()).split(","))
				.map(n -> n.replaceAll("\"", "")).sorted().collect(Collectors.toList()));
		Set<Integer> gaussSums = IntStream.rangeClosed(1, 27)
				.mapToObj(i -> i * (i + 1) / 2).collect(Collectors.toSet());
		System.out.println(gaussSums);
		System.out.println(words.stream().map(Problem042::getCharsValue)
				.filter(value -> gaussSums.contains(value.intValue()))
				.collect(Collectors.counting()));
	}
}
