package euler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;
import java.util.stream.*;

/**
*</div> 
<h2>Powerful digit counts</h2>
<div id="problem_info" class="info">
 <h3>Problem 63</h3>
 <span>Published on Friday, 13th February 2004, 06:00 pm; Solved by 27871; Difficulty rating: 5%</span>
</div> 
<div class="problem_content" role="problem"> 
 <p>The 5-digit number, 16807=7<sup>5</sup>, is also a fifth power. Similarly, the 9-digit number, 134217728=8<sup>9</sup>, is a ninth power.</p> 
 <p>How many <i>n</i>-digit positive integers exist which are also an <i>n</i>th power?</p> 
</div>
<br> 
<br>
*/
public class Problem063 {

    static List<Long> exponents (Double exp) {
        return DoubleStream.iterate(1.0, d -> d < 10, d -> d + 1)
                .filter(lengthPredicate(exp))
                .peek(t -> System.out.println(String.format("%2.0f**%2.0f = %10.0f has %2.0f digits", t, exp, Math.pow(t, exp), exp)))
                .mapToLong(Math::round)
        .collect(ArrayList::new, (r, e) -> r.add(e), List::addAll);

    }

    private static List<BigInteger> bigIntegerExponents(int exp) {
        return Stream.iterate(BigInteger.ONE, bigInteger -> bigInteger.compareTo(BigInteger.TEN) < 0 , bigInteger -> bigInteger.add(BigInteger.ONE))
                .filter(lengthPredicate(exp))
                .peek(bi -> System.out.println(String.format("%d**%d = %d has %d digits", bi, exp, bi.pow(exp), exp)))
                .collect(Collectors.toList());
    }

    private static DoublePredicate lengthPredicate(Double exp) {
        return t -> String.valueOf(Math.round(Math.pow(t, exp))).length() == Math.round(exp);
    }

    private static Predicate<BigInteger> lengthPredicate(Integer exp) {
        return (BigInteger bi) -> bi.pow(exp).toString().length() == exp;
    }

    public static void main(String[] args) {
    System.out.println(IntStream.iterate(1, d -> d < 100, d -> d + 1)
            .mapToObj(Problem063::bigIntegerExponents)
//            .peek(System.out::println)
            .mapToInt(List::size)
    .sum());
//    System.out.println(String.format("%30.0f has %d digits", Math.pow(9,19), String.valueOf(Math.round(Math.pow(9, 19))).length() ));
//    System.out.println(String.format("%30.0f has %d digits", Math.pow(9,20), String.valueOf(Math.round(Math.pow(9, 20))).length()));
}

}
