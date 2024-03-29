package euler;

import org.apache.commons.lang3.tuple.Pair;

import java.security.SecureRandom;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.util.Collections.shuffle;

/**
 * </div>
 * <h2>Monopoly odds</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=84"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 3rd December 2004, 06:00 pm; Solved by 12151;<br>Difficulty rating: 35%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 84</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>In the game, <i>Monopoly</i>, the standard board is set up in the following way:</p>
 * <div class="center">
 * <img src="project/images/p084_monopoly_board.png" alt="p084_monopoly_board.png">
 * </div>
 * <p>A player starts on the GO square and adds the scores on two 6-sided dice to determine the number of squares they advance in a clockwise direction. Without any further rules we would expect to visit each square with equal probability: 2.5%. However, landing on G2J (Go To Jail), CC (community chest), and CH (chance) changes this distribution.</p>
 * <p>In addition to G2J, and one card from each of CC and CH, that orders the player to go directly to jail, if a player rolls three consecutive doubles, they do not advance the result of their 3rd roll. Instead they proceed directly to jail.</p>
 * <p>At the beginning of the game, the CC and CH cards are shuffled. When a player lands on CC or CH they take a card from the top of the respective pile and, after following the instructions, it is returned to the bottom of the pile. There are sixteen cards in each pile, but for the purpose of this problem we are only concerned with cards that order a movement; any instruction not concerned with movement will be ignored and the player will remain on the CC/CH square.</p>
 * <ul>
 * <li>Community Chest (2/16 cards):
 * <ol>
 * <li>Advance to GO</li>
 * <li>Go to JAIL</li>
 * </ol></li>
 * <li>Chance (10/16 cards):
 * <ol>
 * <li>Advance to GO</li>
 * <li>Go to JAIL</li>
 * <li>Go to C1</li>
 * <li>Go to E3</li>
 * <li>Go to H2</li>
 * <li>Go to R1</li>
 * <li>Go to next R (railway company)</li>
 * <li>Go to next R</li>
 * <li>Go to next U (utility company)</li>
 * <li>Go back 3 squares.</li>
 * </ol></li>
 * </ul>
 * <p>The heart of this problem concerns the likelihood of visiting a particular square. That is, the probability of finishing at that square after a roll. For this reason it should be clear that, with the exception of G2J for which the probability of finishing on it is zero, the CH squares will have the lowest probabilities, as 5/8 request a movement to another square, and it is the final square that the player finishes at on each roll that we are interested in. We shall make no distinction between "Just Visiting" and being sent to JAIL, and we shall also ignore the rule about requiring a double to "get out of jail", assuming that they pay to get out on their next turn.</p>
 * <p>By starting at GO and numbering the squares sequentially from 00 to 39 we can concatenate these two-digit numbers to produce strings that correspond with sets of squares.</p>
 * <p>Statistically it can be shown that the three most popular squares, in order, are JAIL (6.24%) = Square 10, E3 (3.18%) = Square 24, and GO (3.09%) = Square 00. So these three most popular squares can be listed with the six-digit modal string: 102400.</p>
 * <p>If, instead of using two 6-sided dice, two 4-sided dice are used, find the six-digit modal string.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem084 {
    final static SecureRandom rand = new SecureRandom();
    final static Random utilRand = new Random();

    public static Pair<Integer, Integer> roll(int sides) {
        return Pair.of(rand.nextInt(sides) + 1, rand.nextInt(sides) + 1);
    }

    public static Pair<Integer, Integer> utilRoll(int sides) {
        return Pair.of(utilRand.nextInt(sides) + 1, utilRand.nextInt(sides) + 1);
    }

    public static Function<Integer,Pair<Integer, Integer>> rollActual = (sides -> roll(sides));
    public static Function<Integer ,Pair<Integer, Integer>> utilRollActual = (sides -> utilRoll(sides));


    private static void makeSomeStats(int max, Function<Integer, Pair<Integer, Integer>> roll) {
        int[] squares = new int[40];
        int actual = 0;
        int sides = 6;
        for (int i = 0; i < max; i++) {
            squares[actual] += 1;
            Pair<Integer, Integer> dice = roll.apply(sides);
            actual = (dice.getLeft() + dice.getRight() + actual) % 40;
        }
//        String format = "square: %02d: %2.4f";
//        for (int i = 0; i < squares.length; i++) {
//            System.out.println(String.format(format, i, 1.0 * squares[i] / max));
//        }
        double[] doubles = new double[40];
        for (int i = 0; i < squares.length; i++) {
            doubles[i] = Double.valueOf(1.0 * squares[i] / max);
        }
        System.out.println(String.format("max    : %2.4f", Arrays.stream(doubles).max().orElse(-1)));
        System.out.println(String.format("min    : %2.4f", Arrays.stream(doubles).min().orElse(-1)));
        System.out.println(String.format("average: %2.4f\n", Arrays.stream(doubles).average().orElse(-1)));
    }

    private static void utilVsSecure(int reallyMax) {
        int max = 1_000;
        for (int i = max; i <= reallyMax; i *= 10) {
            System.out.println(String.format("Max: %10d", i));

            System.out.println("secure random:");
            makeSomeStats(i, rollActual);
            System.out.println("util random:");
            makeSomeStats(i, utilRollActual);
        }
    }

    private enum Card {
        GO(0),
        JAIL(10),
        C1(11),
        E3(24),
        H2(39),
        R1(5),
        R_1(-1),
        R_2(-2),
        U(-1),
        BACK(3),
        NONE(0);

        private int index;

        Card(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        private static List<Card> chStack = shuffled(values(), 16);
        private static List<Card> ccStack = shuffled(new Card[]{JAIL, GO}, 16);
        private static int ccIdx = 0;
        private static int chIdx = 0;

        public static Card chCard() {
            Card result = chStack.get(chIdx);
            chIdx = (chIdx + 1) % 16;
            return result;
        }

        public static Card ccCard() {
            Card result = ccStack.get(ccIdx);
            ccIdx = (ccIdx + 1) % 16;
            return result;
        }

        public static List<Card> shuffled(Card[] values, int stackSize) {
            ArrayList<Card> result = new ArrayList<>(Arrays.asList(values));
            while (result.size() < stackSize) {
                result.add(NONE);
            }
            shuffle(result);
            return result;
        }
    }


    private static int handleCC(int rollSquare) {
        Card drawn = Card.ccCard();
        return switch (drawn) {
            case GO, JAIL -> drawn.getIndex();
            default -> rollSquare;
        };
    }

    private static int handleCH(int rollSquare) {
        Card drawn = Card.chCard();
        return switch (drawn) {
            case GO, JAIL, C1, E3, H2, R1 -> drawn.getIndex();
            case R_1, R_2 -> {
                if (rollSquare == 7) {
                    yield 15;
                }
                if (rollSquare == 22)
                    yield 25;
                yield 5;
            }
            case U -> {
                if (rollSquare == 22)
                    yield 28;
                yield 12;
            }
            case BACK -> {
                int result = rollSquare - 3;
                if(result == 33) {
                    yield handleCC(result);
                }
                yield result;
            }
            default -> rollSquare;
        };
    }

    private static int handleSquare(int rollSquare) {
        // case G2J
        if (rollSquare == 30) {
            return 10;
        }
        //   community chest
        if (rollSquare == 2 || rollSquare == 17 || rollSquare == 33) {
            return handleCC(rollSquare);
        }
        // chance
        if (rollSquare == 7 || rollSquare == 22 || rollSquare == 36) {
            return handleCH(rollSquare);
        }
        return rollSquare;
    }

    public static void main(String[] args) {
//        utilVsSecure(100_000_000);
        int max = 10_000_000;
        int sides = 4;
//        int[] squares = new int[40];
        Map<Integer, Integer> squares = new HashMap();
        for (int i = 0; i < 40; i++) {
            squares.put(i, 0);
        }
        int actual = 0;
        int doublesCount = 0;
        int doubleJailing = 0;
        for (int i = 0; i <= max; i++) {
            squares.merge(actual, 1, Integer::sum);
            Pair<Integer, Integer> dice = rollActual.apply(sides);
            if (dice.getLeft() == dice.getRight()) {
                doublesCount +=1;
            } else {
                doublesCount = 0;
            }
            if (doublesCount == 3) {
                doubleJailing++;
                doublesCount = 0;
                actual = 10;
            } else {
                actual = handleSquare((dice.getLeft() + dice.getRight() + actual) % 40);
            }
        }
        Comparator<Map.Entry<Integer, Integer>> valueComparator = Comparator.comparingInt(Map.Entry::getValue);
        squares.entrySet().stream()
                .sorted(valueComparator.reversed())
                .forEach(e -> System.out.println(String.format("%02d : %1.2f %%", e.getKey(), 100.0 * e.getValue() /max)));
        System.out.println(String.format("double jailed %d times", doubleJailing));
    }

}
