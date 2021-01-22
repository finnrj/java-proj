package euler;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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

    public static int roll() {
        return rand.nextInt(6) + rand.nextInt(6) + 2;
    }

    public static int utilRoll() {
        return utilRand.nextInt(6) + utilRand.nextInt(6) + 2;
    }

    public static Function<Integer, Integer> rollActual = (actual -> (actual + roll()) % 40);
    public static Function<Integer, Integer> utilRollActual = (actual -> (actual + utilRoll()) % 40);


    private static void makeSomeStats(int max, Function<Integer, Integer> roll) {
        int[] squares = new int[40];
        int actual = 0;
        for (int i = 0; i < max; i++) {
            squares[actual] += 1;
            actual = roll.apply(actual);
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

    private enum Square {
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
        NONE_1(0),
        NONE_2(0),
        NONE_3(0),
        NONE_4(0),
        NONE_5(0),
        NONE_6(0);

        private int index;

        Square(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }


        private static List<Square> stack = shuffled();

        public static Square nextCard(int idx) {
            return stack.get(idx);
        }

        public static List<Square> shuffled() {
            List<Square> result = Arrays.asList(values());
            shuffle(result);
            return result;
        }
    }

    private static int ccIdx = 0;

    private static int handleCC(int rollSquare) {
        Square drawn = Square.nextCard(ccIdx);
        ccIdx = (ccIdx + 1) % 18;
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
            case BACK -> rollSquare - 3;
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
            handleCC(rollSquare);
        }
        return 0;
    }

    public static void main(String[] args) {
//        utilVsSecure(100_000_000);
        int max = 1_000_000;
        int[] squares = new int[40];
        int actual = 0;
//        for (int i = 0; i <= max; i++) {
//            squares[actual] += 1;
//            actual = handleSquare(rollActual.apply(actual));
//        }
        for (int i = 0; i < 8; i++) {
            System.out.println(Square.shuffled());
        }

    }

}
