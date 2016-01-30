package euler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * </div>
 * <h2>Poker hands</h2> <div id="problem_info" class="info">
 * <h3>Problem 54</h3> <span>Published on Friday, 10th October 2003, 06:00 pm;
 * Solved by 22404; Difficulty rating: 10%</span> </div>
 * <div class="problem_content" role="problem">
 * <p>
 * In the card game poker, a hand consists of five cards and are ranked, from
 * lowest to highest, in the following way:
 * </p>
 * <ul>
 * <li><b>High Card</b>: Highest value card.</li>
 * <li><b>One Pair</b>: Two cards of the same value.</li>
 * <li><b>Two Pairs</b>: Two different pairs.</li>
 * <li><b>Three of a Kind</b>: Three cards of the same value.</li>
 * <li><b>Straight</b>: All cards are consecutive values.</li>
 * <li><b>Flush</b>: All cards of the same suit.</li>
 * <li><b>Full House</b>: Three of a kind and a pair.</li>
 * <li><b>Four of a Kind</b>: Four cards of the same value.</li>
 * <li><b>Straight Flush</b>: All cards are consecutive values of same suit.
 * </li>
 * <li><b>Royal Flush</b>: Ten, Jack, Queen, King, Ace, in same suit.</li>
 * </ul>
 * <p>
 * The cards are valued in the order:<br>
 * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
 * </p>
 * <p>
 * If two players have the same ranked hands then the rank made up of the
 * highest value wins; for example, a pair of eights beats a pair of fives (see
 * example 1 below). But if two ranks tie, for example, both players have a pair
 * of queens, then highest cards in each hand are compared (see example 4
 * below); if the highest cards tie then the next highest cards are compared,
 * and so on.
 * </p>
 * <p>
 * Consider the following five hands dealt to two players:
 * </p>
 * <div style="text-align:center;">
 * <table>
 * <tbody>
 * <tr>
 * <td><b>Hand</b></td>
 * <td>&nbsp;</td>
 * <td><b>Player 1</b></td>
 * <td>&nbsp;</td>
 * <td><b>Player 2</b></td>
 * <td>&nbsp;</td>
 * <td><b>Winner</b></td>
 * </tr>
 * <tr>
 * <td style="vertical-align:top;"><b>1</b></td>
 * <td>&nbsp;</td>
 * <td>5H 5C 6S 7S KD<br>
 * <div class="note"> Pair of Fives </div></td>
 * <td>&nbsp;</td>
 * <td>2C 3S 8S 8D TD<br>
 * <div class="note"> Pair of Eights </div></td>
 * <td>&nbsp;</td>
 * <td style="vertical-align:top;">Player 2</td>
 * </tr>
 * <tr>
 * <td style="vertical-align:top;"><b>2</b></td>
 * <td>&nbsp;</td>
 * <td>5D 8C 9S JS AC<br>
 * <div class="note"> Highest card Ace </div></td>
 * <td>&nbsp;</td>
 * <td>2C 5C 7D 8S QH<br>
 * <div class="note"> Highest card Queen </div></td>
 * <td>&nbsp;</td>
 * <td style="vertical-align:top;">Player 1</td>
 * </tr>
 * <tr>
 * <td style="vertical-align:top;"><b>3</b></td>
 * <td>&nbsp;</td>
 * <td>2D 9C AS AH AC<br>
 * <div class="note"> Three Aces </div></td>
 * <td>&nbsp;</td>
 * <td>3D 6D 7D TD QD<br>
 * <div class="note"> Flush with Diamonds </div></td>
 * <td>&nbsp;</td>
 * <td style="vertical-align:top;">Player 2</td>
 * </tr>
 * <tr>
 * <td style="vertical-align:top;"><b>4</b></td>
 * <td>&nbsp;</td>
 * <td>4D 6S 9H QH QC<br>
 * <div class="note"> Pair of Queens <br>
 * Highest card Nine </div></td>
 * <td>&nbsp;</td>
 * <td>3D 6D 7H QD QS<br>
 * <div class="note"> Pair of Queens <br>
 * Highest card Seven </div></td>
 * <td>&nbsp;</td>
 * <td style="vertical-align:top;">Player 1</td>
 * </tr>
 * <tr>
 * <td style="vertical-align:top;"><b>5</b></td>
 * <td>&nbsp;</td>
 * <td>2H 2D 4C 4D 4S<br>
 * <div class="note"> Full House <br>
 * With Three Fours </div></td>
 * <td>&nbsp;</td>
 * <td>3C 3D 3S 9S 9D<br>
 * <div class="note"> Full House <br>
 * with Three Threes </div></td>
 * <td>&nbsp;</td>
 * <td style="vertical-align:top;">Player 1</td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * <p>
 * The file, <a href="project/resources/p054_poker.txt">poker.txt</a>, contains
 * one-thousand random hands dealt to two players. Each line of the file
 * contains ten cards (separated by a single space): the first five are Player
 * 1's cards and the last five are Player 2's cards. You can assume that all
 * hands are valid (no invalid characters or repeated cards), each player's hand
 * is in no specific order, and in each hand there is a clear winner.
 * </p>
 * <p>
 * How many hands does Player 1 win?
 * </p>
 * </div> <br>
 * <br>
 */
public class Problem054 {

	static Map<String, Integer> colors = new HashMap<String, Integer>();

	static {
		colors.put("S", 3);
		colors.put("H", 2);
		colors.put("D", 1);
		colors.put("C", 0);
	}

	static Map<String, Integer> rangs = new HashMap<String, Integer>();

	static {
		rangs.put("A", 14);
		rangs.put("K", 13);
		rangs.put("Q", 12);
		rangs.put("J", 11);
		rangs.put("T", 10);
	}

	private static final Path POKER_PATH = Paths.get("src", "main", "docs",
			"poker.txt");

	private static List<List<Integer>> fetchHands() {
		try (Stream<String> lines = Files.lines(POKER_PATH)) {
			return lines.filter(line -> !line.isEmpty()).flatMap(Problem054::convert)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		for (List<Integer> s : fetchHands()) {
			System.out.println(s);
		}
	}

	public static Stream<List<Integer>> convert(String hands) {
		String[] split = hands.split("\\s");
		List<Integer> first = Arrays.stream(Arrays.copyOfRange(split, 0, 5))
				.mapToInt(Problem054::card2Int).boxed().collect(Collectors.toList());
		List<Integer> second = Arrays
				.stream(Arrays.copyOfRange(split, 5, split.length))
				.mapToInt(Problem054::card2Int).boxed().collect(Collectors.toList());
		return Stream.<List<Integer>> of(first, second);
	}

	private static Integer card2Int(String card) {
		String first = card.substring(0, 1);
		Integer rang = rangs.containsKey(first) ? rangs.get(first)
				: Integer.parseInt(first);
		int result = 10 * rang + colors.get(card.substring(1));
		return result;
	}

	Predicate<List<Integer>> sameColor = is -> is.stream().map(i -> i % 10)
			.distinct().count() == 1;

	Predicate<List<Integer>> sequential = is -> {
		IntSummaryStatistics stats = is.stream().mapToInt(i -> i / 10)
				.summaryStatistics();
		return stats.getMax() - stats.getMin() == 4
				&& (int) stats.getAverage() == stats.getMin() + 2;
	};

	BiPredicate<List<Integer>, Predicate<Integer>> maxcard = (li,
			maxTest) -> maxTest
					.test(li.stream().mapToInt(i -> i / 10).max().getAsInt());

	Predicate<List<Integer>> fourOfAKind = is -> {
		Map<Integer, Long> countMap = is.stream().mapToInt(i -> i / 10).boxed()
				.collect(
						Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return countMap.values().stream().max(Long::compareTo).get() == 4;
	};

	Predicate<List<Integer>> threeOfAKind = is -> {
		Map<Integer, Long> countMap = is.stream().mapToInt(i -> i / 10).boxed()
				.collect(
						Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return countMap.size() == 3
				&& countMap.values().stream().max(Long::compareTo).get() == 3;
	};

	Predicate<List<Integer>> fullHouse = is -> {
		Map<Integer, Long> countMap = is.stream().mapToInt(i -> i / 10).boxed()
				.collect(
						Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return countMap.size() == 2
				&& countMap.values().stream().max(Long::compareTo).get() == 3;
	};

	Predicate<List<Integer>> twoPairs = is -> {
		Map<Integer, Long> countMap = is.stream().mapToInt(i -> i / 10).boxed()
				.collect(
						Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return countMap.size() == 3
				&& countMap.values().stream().max(Long::compareTo).get() == 2;
	};

	Predicate<List<Integer>> onePair = is -> {
		Map<Integer, Long> countMap = is.stream().mapToInt(i -> i / 10).boxed()
				.collect(
						Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return countMap.size() == 4;
	};

	public boolean isRoyalFlush(List<Integer> hand) {
		return isStraightFlush(hand) && maxcard.test(hand, max -> max == 14);
	}

	public boolean isStraightFlush(List<Integer> hand) {
		return sameColor.test(hand) && sequential.test(hand);
	}

	public boolean isFourOfAKind(List<Integer> hand) {
		return fourOfAKind.test(hand);
	}

	public boolean isFullHouse(List<Integer> hand) {
		return fullHouse.test(hand);
	}

	public boolean isFlush(List<Integer> hand) {
		return sameColor.test(hand);
	}

	public boolean isStraight(List<Integer> hand) {
		return sequential.test(hand);
	}

	public boolean isThreeOfAKind(List<Integer> hand) {
		return threeOfAKind.test(hand);
	}

	public boolean isTwoPairs(List<Integer> hand) {
		return twoPairs.test(hand);
	}

	public boolean isOnePair(List<Integer> hand) {
		return onePair.test(hand);
	}

}
