package euler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem022 {

	public static Long getCharsValue(String name) {
		return name
				.chars()
				.mapToLong(
						ch -> Character.getNumericValue(ch)
								- Character.getNumericValue('A') + 1).sum();
	}

	public static void main(String[] args) throws IOException {

		List<String> names = new ArrayList<String>(Stream
				.of(Files.lines(Paths.get(".", "docs/names.txt"))
						.collect(Collectors.joining()).split(","))
				.map(n -> n.replaceAll("\"", "")).sorted().collect(Collectors.toList()));
		System.out.println(names.stream()
				// .peek(n -> System.out.println(n))
				.mapToLong(name -> getCharsValue(name) * (names.indexOf(name) + 1))
				.sum());
	}
}
