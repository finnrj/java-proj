package euler;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * </div>
 * <h2>Counting summations</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=76"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 13th August 2004, 06:00 pm; Solved by 27955;<br>Difficulty rating: 10%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 76</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>It is possible to write five as a sum in exactly six different ways:</p>
 * <p class="margin_left">4 + 1<br> 3 + 2<br> 3 + 1 + 1<br> 2 + 2 + 1<br> 2 + 1 + 1 + 1<br> 1 + 1 + 1 + 1 + 1</p>
 * <p>How many different ways can one hundred be written as a sum of at least two positive integers?</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem076 {

    public static void main(String[] args) {
        int cellCount = 10;
        Map<Integer, Integer> table = IntStream.rangeClosed(1, cellCount).boxed()
                .collect(Collectors.toMap(Function.identity(), (k) -> 0));
        System.out.println(table.size());
        for (int toAdd = 1; toAdd < cellCount; toAdd++) {
            for (int tableCell = toAdd + 1; tableCell <= cellCount; tableCell++) {
                int previous = tableCell - toAdd;
                int newAddition = previous >= toAdd ? 1 : 0;
                System.out.println(String.format("%d, %d:%d, %d", tableCell, previous, table.get(previous), newAddition));
                table.put(tableCell, table.get(tableCell)
                        + table.get(previous)
                        + newAddition);
            }
            System.out.println("after " + toAdd + ":" +table);
        }
        System.out.println(table);
    }

}
