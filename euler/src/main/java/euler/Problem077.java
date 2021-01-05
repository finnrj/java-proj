package euler;

import utils.Utils;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * </div>
 * <h2>Prime summations</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=77"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 27th August 2004, 06:00 pm; Solved by 18580;<br>Difficulty rating: 25%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 77</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>It is possible to write ten as the sum of primes in exactly five different ways:</p>
 * <p class="margin_left">7 + 3<br> 5 + 5<br> 5 + 3 + 2<br> 3 + 3 + 2 + 2<br> 2 + 2 + 2 + 2 + 2</p>
 * <p>What is the first value which can be written as the sum of primes in over five thousand different ways?</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem077 {

    public static void main(String[] args) {
        long cellCount = 100;
        Map<Long, Long> table = LongStream.rangeClosed(1L, cellCount).boxed()
                .collect(Collectors.toMap(Function.identity(), (k) -> 0L));

        Utils.getPrimes(p -> p < cellCount).forEach(p -> fillTable(p, cellCount, table));
        System.out.println(table);
        System.out.println(table.entrySet().stream().filter(e -> e.getValue() > 5_000)
                .min(Comparator.comparing(Map.Entry::getKey)));
    }

    private static void fillTable(long prime, long cellCount, Map<Long, Long> table) {
        for (long tableCell = prime + 2; tableCell <= cellCount; tableCell++) {
            long previous = tableCell - prime;
            long newAddition = previous > prime ? 0 : 1;
            table.put(tableCell, table.get(tableCell)
                    + table.get(previous)
                    + newAddition);
        }
    }

}
