package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProblemDownloader {
	private static final String EULER_URL = "https://projecteuler.net";
	private static final String PROBLEM_URL = String.join("/", EULER_URL,
			"problem=%d");
	private static final String TARGET_PATH = String.join(File.separator, "src",
			"main", "java", "euler");
	private static final String DOCS_PATH = String.join(File.separator, "src",
			"main", "docs");

	public static void main(String[] args) {
		for (int i = 76; i <= 88; i++) {
			ProblemDownloader.createProblemFile(i);
		}
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
		try {
			Element target = Jsoup.connect(String.format(PROBLEM_URL, problemNumber))
					.get().getElementById("content");
			fetchLinkFiles(target);
			String[] prettyPrintedHtml = target.html().split("\n");
			String[] copyOfRange = Arrays.copyOfRange(prettyPrintedHtml, 2,
					prettyPrintedHtml.length);
			result.add("/**");
			result.add("*" + String.join("\n", copyOfRange));
			result.add("*/");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	private static void fetchLinkFiles(Element targetHtml) {
		Elements links = targetHtml.select("a[href]");
		for (Element link : links) {
			Path target = Paths.get(DOCS_PATH, link.text());
			try {
				Files.createFile(target);
				Files.write(target, Arrays.asList(Jsoup.connect(link.attr("abs:href"))
						.ignoreContentType(true).execute().body()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
