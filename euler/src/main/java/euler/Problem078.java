package euler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * </div>
 * <h2>Coin partitions</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=78"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 10th September 2004, 06:00 pm; Solved by 16293;<br>Difficulty rating: 30%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 78</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>Let p(<i>n</i>) represent the number of different ways in which <i>n</i> coins can be separated into piles. For example, five coins can be separated into piles in exactly seven different ways, so p(5)=7.</p>
 * <div class="margin_left">
 * OOOOO
 * <br> OOOO&nbsp; &nbsp;O
 * <br> OOO&nbsp; &nbsp;OO
 * <br> OOO&nbsp; &nbsp;O&nbsp; &nbsp;O
 * <br> OO&nbsp; &nbsp;OO&nbsp; &nbsp;O
 * <br> OO&nbsp; &nbsp;O&nbsp; &nbsp;O&nbsp; &nbsp;O
 * <br> O&nbsp; &nbsp;O&nbsp; &nbsp;O&nbsp; &nbsp;O&nbsp; &nbsp;O
 * </div>
 * <p>Find the least value of <i>n</i> for which p(<i>n</i>) is divisible by one million.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem078 {

    private static void ext(int cellCount, BigInteger[] tableList) {
        for (int toAdd = 1; toAdd < cellCount; toAdd++) {
            if (toAdd % 10_000 == 0) {
//                System.out.println(toAdd);
            }
            for (int tableCell = toAdd + 1; tableCell < cellCount; tableCell++) {
                int previous = tableCell - toAdd;
                BigInteger newAddition = previous > toAdd ? BigInteger.ZERO : BigInteger.ONE;
                tableList[tableCell] = tableList[tableCell].add(tableList[previous]).add(newAddition);
            }
            if ((tableList[toAdd - 1].add(BigInteger.ONE)
                    .divideAndRemainder(BigInteger.valueOf(1_000_000)))[1] == BigInteger.ZERO) {
                System.out.println(
                        String.format("found: %d : %d", toAdd - 1, tableList[toAdd - 1].add(BigInteger.ONE)));
                return;
            }

        }
    }

    public static void main(String[] args) {
        int cellCount = 60_000;
        Instant start = Instant.now();

        System.out.println("cellcount: " + cellCount);
        BigInteger[] tableList = new BigInteger[(cellCount + 1)];
        for (int j = 0; j < tableList.length; j++) {
            tableList[j] = BigInteger.ZERO;
        }

        ext(cellCount, tableList);
        System.out.println("are we there yet?");
        System.out.println("it took       " + Duration.between(start, Instant.now()));
        print(tableList, BigInteger.ONE);
    }


    private static void print(BigInteger[] tableList) {
        for (int i = 0; i < 100; i++) {
            System.out.println(String.format("%d : %d, %s", i, tableList[i], tableList[i]));
        }
    }

    private static void print(BigInteger[] tableList, BigInteger toAdd) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(
                        String.format("/home/finn/tablelists/tablelist_%08d.txt", tableList.length)))) {
            for (int i = 0; i < 100; i++) {
//                System.out.println(String.format("%d : %d", i, tableList[i].add(toAdd)));
                writer.println(String.format("%02d : %10d", i, tableList[i].add(toAdd)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

