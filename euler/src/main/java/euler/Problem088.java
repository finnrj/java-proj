package euler;

import utils.Combinations;
import utils.Utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * </div>
 * <h2>Product-sum numbers</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=88"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 4th February 2005, 06:00 pm; Solved by 9642;<br>Difficulty rating: 40%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 88</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>A natural number, N, that can be written as the sum and product of a given set of at least two natural numbers, {<i>a</i><sub>1</sub>, <i>a</i><sub>2</sub>, ... , <i>a</i><sub><i>k</i></sub>} is called a product-sum number: N = <i>a</i><sub>1</sub> + <i>a</i><sub>2</sub> + ... + <i>a</i><sub><i>k</i></sub> = <i>a</i><sub>1</sub> × <i>a</i><sub>2</sub> × ... × <i>a</i><sub><i>k</i></sub>.</p>
 * <p>For example, 6 = 1 + 2 + 3 = 1 × 2 × 3.</p>
 * <p>For a given set of size, <i>k</i>, we shall call the smallest N with this property a minimal product-sum number. The minimal product-sum numbers for sets of size, <i>k</i> = 2, 3, 4, 5, and 6 are as follows.</p>
 * <p class="margin_left"><i>k</i>=2: 4 = 2 × 2 = 2 + 2<br><i>k</i>=3: 6 = 1 × 2 × 3 = 1 + 2 + 3<br><i>k</i>=4: 8 = 1 × 1 × 2 × 4 = 1 + 1 + 2 + 4<br><i>k</i>=5: 8 = 1 × 1 × 2 × 2 × 2 = 1 + 1 + 2 + 2 + 2<br><i>k</i>=6: 12 = 1 × 1 × 1 × 1 × 2 × 6 = 1 + 1 + 1 + 1 + 2 + 6</p>
 * <p>Hence for 2≤<i>k</i>≤6, the sum of all the minimal product-sum numbers is 4+6+8+12 = 30; note that 8 is only counted once in the sum.</p>
 * <p>In fact, as the complete set of minimal product-sum numbers for 2≤<i>k</i>≤12 is {4, 6, 8, 12, 15, 16}, the sum is 61.</p>
 * <p>What is the sum of all the minimal product-sum numbers for 2≤<i>k</i>≤12000?</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem088 {

    record NumberFactors(long number, List<Long> factors) {
    }


    public static void main(String[] args) {
        List<Long> ps = Utils.getPrimes(p -> p < 6_000).collect(Collectors.toList());
        ArrayList<NumberFactors> simplePrimeProducts = new ArrayList();
        for (Long p_1: ps) {
            if (p_1 > Math.sqrt(12_000)) {
                break;
            }
            for (Long p_2: ps) {
                if (p_2 < p_1) {
                    continue;
                }
                simplePrimeProducts.add(new NumberFactors(p_1 * p_2, List.of(p_1, p_2)));
            }
        }
        for (NumberFactors nf: simplePrimeProducts) {
            System.out.println( nf);
        }
    }
}
