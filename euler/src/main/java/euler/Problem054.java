package euler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Maps;

import utils.Utils;

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

	private static final int MAX_SHIFT = 20;

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

	static Predicate<List<Integer>> sameColor = is -> is.stream().map(i -> i % 10)
			.distinct().count() == 1;

	static Predicate<List<Integer>> sequential = is -> {
		IntSummaryStatistics stats = is.stream().mapToInt(i -> i / 10)
				.summaryStatistics();
		return stats.getMax() - stats.getMin() == 4
				&& (int) stats.getAverage() == stats.getMin() + 2;
	};

	static Predicate<List<Integer>> containsAce = is -> is.stream()
			.mapToInt(i -> i / 10).max().getAsInt() == 14;

	static Predicate<List<Integer>> royalFlush = sameColor.and(sequential)
			.and(containsAce);

	static Predicate<List<Integer>> straightFlush = sameColor.and(sequential)
			.and(containsAce.negate());

	static Predicate<List<Integer>> fourOfAKind = is -> {
		Map<Integer, Long> countMap = mapToCounts(is);
		return countMap.values().stream().max(Long::compareTo).get() == 4;
	};

	static Predicate<List<Integer>> flush = sameColor.and(sequential.negate());

	static Predicate<List<Integer>> straight = sequential.and(sameColor.negate());

	static Predicate<List<Integer>> threeOfAKind = is -> {
		Map<Integer, Long> countMap = mapToCounts(is);
		return countMap.size() == 3
				&& countMap.values().stream().max(Long::compareTo).get() == 3;
	};

	static Predicate<List<Integer>> fullHouse = is -> {
		Map<Integer, Long> countMap = mapToCounts(is);
		return countMap.size() == 2
				&& countMap.values().stream().max(Long::compareTo).get() == 3;
	};

	static Predicate<List<Integer>> twoPairs = is -> {
		Map<Integer, Long> countMap = mapToCounts(is);
		return countMap.size() == 3
				&& countMap.values().stream().max(Long::compareTo).get() == 2;
	};

	static Predicate<List<Integer>> onePair = is -> {
		Map<Integer, Long> countMap = mapToCounts(is);
		return countMap.size() == 4;
	};

	static private Map<Integer, Long> mapToCounts(List<Integer> is) {
		return is.stream().mapToInt(i -> i / 10).boxed().collect(
				Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	static Map<Predicate<List<Integer>>, Integer> scores = Maps.newHashMap();

	/* @formatter:off */
	static {
		scores.put(onePair,       1 << MAX_SHIFT);
		scores.put(twoPairs,      2 << MAX_SHIFT);
		scores.put(threeOfAKind,  3 << MAX_SHIFT);
		scores.put(straight,      4 << MAX_SHIFT);
		scores.put(flush,         5 << MAX_SHIFT);
		scores.put(fullHouse,     6 << MAX_SHIFT);
		scores.put(fourOfAKind,   7 << MAX_SHIFT);
		scores.put(straightFlush, 8 << MAX_SHIFT);
		scores.put(royalFlush,    9 << MAX_SHIFT);
	}
	/* @formatter:on */

	static Map<Integer, String> scores2hand = Maps.newHashMap();

	static {
		scores2hand.put(0, "highest card");
		scores2hand.put(1, "one pair");
		scores2hand.put(2, "two pairs");
		scores2hand.put(3, "three of a kind");
		scores2hand.put(4, "straight");
		scores2hand.put(5, "flush");
		scores2hand.put(6, "full house");
		scores2hand.put(7, "four of a kind");
		scores2hand.put(8, "straight flush");
		scores2hand.put(9, "royal flush");
	}

	public static void main(String[] args) {
		List<Integer> convert2Score = convert2Score(fetchHands());
		int playerOneScore = 0;
		for (int i = 0; i < convert2Score.size(); i += 2) {
			Integer player1 = convert2Score.get(i);
			Integer player2 = convert2Score.get(i + 1);
			// System.out.println(String.format("scores: %8d, %8d", player1,
			// player2));
			System.out.println(
					String.format("%-15s %s %-15s", scores2hand.get(player1 >> MAX_SHIFT),
							(player1 > player2) ? ">" : "<",
							scores2hand.get(player2 >> MAX_SHIFT)));
			if (player1 > player2) {
				playerOneScore++;
			}
		}
		System.out
				.println(String.format("player one wins %d hands", playerOneScore));
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

	public static Stream<List<Integer>> convert(String hands) {
		String[] split = hands.split("\\s");
		List<Integer> first = Arrays.stream(Arrays.copyOfRange(split, 0, 5))
				.mapToInt(Problem054::card2Int).sorted().boxed()
				.collect(Collectors.toList());
		List<Integer> second = Arrays
				.stream(Arrays.copyOfRange(split, 5, split.length))
				.mapToInt(Problem054::card2Int).sorted().boxed()
				.collect(Collectors.toList());
		return Stream.<List<Integer>> of(first, second);
	}

	private static Integer card2Int(String card) {
		String first = card.substring(0, 1);
		Integer rang = rangs.containsKey(first) ? rangs.get(first)
				: Integer.parseInt(first);
		int result = 10 * rang + colors.get(card.substring(1));
		return result;
	}

	private static Integer hand2Score(List<Integer> hand) {
		return scores.getOrDefault(scores.keySet().stream()
				.filter(p -> p.test(hand) == true).findFirst().orElse(null), 0);
	}

	private static Integer cardScores(List<Integer> hand) {
		Stream<Integer> indices = IntStream.range(0, hand.size()).boxed();
		Map<Integer, Long> mapToCounts = mapToCounts(hand);

		Comparator<? super Integer> handComparator = (o1, o2) -> {
			Long count1 = mapToCounts.get(o1);
			Long count2 = mapToCounts.get(o2);
			if (count1 != count2)
				return count1.intValue() - count2.intValue();
			return o1 - o2;
		};

		Stream<Integer> cards = hand.stream().mapToInt(i -> i / 10).boxed()
				.sorted(handComparator);
		Stream<Integer> zip = Utils.zip(cards, indices, (c, i) -> c << 4 * i);
		return zip.mapToInt(Integer::intValue).sum();
	}

	public static List<Integer> convert2Score(List<List<Integer>> hands) {
		return hands.stream()
				.map(hand -> new Integer(hand2Score(hand) + cardScores(hand)))
				.collect(Collectors.toList());
	}
}
