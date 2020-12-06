package euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * </div>
 * <h2>Convergents of e</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 65</h3>
 * <span>Published on Friday, 12th March 2004, 06:00 pm; Solved by 19500; Difficulty rating: 15%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>The square root of 2 can be written as an infinite continued fraction.</p>
 * <div style="margin-left:20px;">
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>√2 = 1 +</td>
 * <td colspan="4" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 + ...</td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * <p>The infinite continued fraction can be written, √2 = [1;(2)], (2) indicates that 2 repeats <i>ad infinitum</i>. In a similar way, √23 = [4;(1,3,1,8)].</p>
 * <p>It turns out that the sequence of partial values of continued fractions for square roots provide the best rational approximations. Let us consider the convergents for √2.</p>
 * <div style="margin-left:20px;">
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>1 +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>= 3/2</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 2
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * </tbody>
 * </table>
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>1 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>= 7/5</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 2
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * </tbody>
 * </table>
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>1 +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>= 17/12</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 2
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * </tbody>
 * </table>
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>1 +</td>
 * <td colspan="4" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>= 41/29</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 2
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * <p>Hence the sequence of the first ten convergents for √2 are:</p>
 * <div class="note">
 * 1, 3/2, 7/5, 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378, ...
 * </div>
 * <p>What is most surprising is that the important mathematical constant,<br><i>e</i> = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2<i>k</i>,1, ...].</p>
 * <p>The first ten terms in the sequence of convergents for <i>e</i> are:</p>
 * <div class="note">
 * 2, 3, 8/3, 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...
 * </div>
 * <p>The sum of digits in the numerator of the 10<sup>th</sup> convergent is 1+4+5+7=17.</p>
 * <p>Find the sum of digits in the numerator of the 100<sup>th</sup> convergent of the continued fraction for <i>e</i>.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem065 {

    static IntStream generateExpConvergence(int count) {
        return IntStream.iterate(0, val -> val < count, prev -> prev + 1)
                .map(generateFromIndex);
    }


    static IntUnaryOperator generateFromIndex = (idx -> {
        if (idx == 0) {
            return 2;
        } else if (idx % 3 == 2) {
            return (idx / 3 + 1) * 2;
        } else {
            return 1;
        }
    });

    private static List<Integer> multiplicatators = generateExpConvergence(100).boxed().collect(Collectors.toList());

    private static List<BigInteger> generateNominators(int idx, List<BigInteger> accumulator) {
        if (idx == 0) {
            BigInteger value = BigInteger.valueOf(multiplicatators.get(0));
            accumulator.add(idx, value);
        } else if (idx == 1) {
            BigInteger value = BigInteger.valueOf(multiplicatators.get(idx))
                    .multiply(accumulator.get(idx - 1))
                    .add(BigInteger.ONE);
            accumulator.add(idx, value);
        } else {
            BigInteger value = BigInteger.valueOf(multiplicatators.get(idx))
                    .multiply(accumulator.get(idx - 1))
                    .add(accumulator.get(idx - 2));
            accumulator.add(idx, value);
        }
        return accumulator;
    }


    public static void main(String[] args) {
        System.out.println(Problem065.multiplicatators);
        List<BigInteger> accumulator = new ArrayList<>();
        IntStream.iterate(0, idx -> idx < multiplicatators.size(), idx -> idx + 1)
                .mapToObj(idx -> generateNominators(idx, accumulator)).forEach(System.out::println);
//                .map(i -> String.valueOf(i).chars().map(Character::getNumericValue).sum())
//                .boxed().collect(Collectors.toList()));
//        6963524437876961749120273824619538346438023188214475670667
//        sum = 272
    }

}
