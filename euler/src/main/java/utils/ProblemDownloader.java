package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ProblemDownloader {
	private static final String EULER_URL = "https://projecteuler.net/problem=%d";
	private static final String TARGET_PATH = String.join(File.separator, "src",
			"main", "java", "euler");

	public static void main(String[] args) {
		createProblemFile(52);
	}

	public static void createProblemFile(int problemNumber) {
		Path target = Paths.get(TARGET_PATH,
				String.format("Problem%03d.java", problemNumber));
		try {
			Files.createFile(target);
			Files.write(target, fileLines(problemNumber));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> fetchDescription(int problemNumber) {
		ArrayList<String> result = new ArrayList<String>();
		result.add("/**");
		try {
			Document doc = Jsoup.connect(String.format(EULER_URL, problemNumber))
					.get();
			Element panel = doc.getElementById("content");
			result.addAll(panel.select("p").stream().map(e -> "* " + e.text())
					.collect(Collectors.toList()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		result.add("*/");
		return result;
	}

	private static List<String> fileLines(int problemNumber) {
		ArrayList<String> result = new ArrayList<String>();
		result.add("package euler;");
		result.add("");
		result.addAll(fetchDescription(problemNumber));
		result.addAll(Arrays.asList(
				String.format("public class Problem%03d {", problemNumber), "",
				"public static void main(String[] args) {", "", "}", "", "}"));
		return result;
	}
}
