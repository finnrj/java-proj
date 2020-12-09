package euler;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * </div>
 * <h2>Diophantine equation</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 66</h3>
 * <span>Published on Friday, 26th March 2004, 06:00 pm; Solved by 12357; Difficulty rating: 25%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>Consider quadratic Diophantine equations of the form:</p>
 * <p style="text-align:center;"><i>x</i><sup>2</sup> – D<i>y</i><sup>2</sup> = 1</p>
 * <p>For example, when D=13, the minimal solution in <i>x</i> is 649<sup>2</sup> – 13×180<sup>2</sup> = 1.</p>
 * <p>It can be assumed that there are no solutions in positive integers when D is square.</p>
 * <p>By finding minimal solutions in <i>x</i> for D = {2, 3, 5, 6, 7}, we obtain the following:</p>
 * <p style="margin-left:20px;">3<sup>2</sup> – 2×2<sup>2</sup> = 1<br> 2<sup>2</sup> – 3×1<sup>2</sup> = 1<br><span style="color:#dd0000;font-weight:bold;">9</span><sup>2</sup> – 5×4<sup>2</sup> = 1<br> 5<sup>2</sup> – 6×2<sup>2</sup> = 1<br> 8<sup>2</sup> – 7×3<sup>2</sup> = 1</p>
 * <p>Hence, by considering minimal solutions in <i>x</i> for D ≤ 7, the largest <i>x</i> is obtained when D=5.</p>
 * <p>Find the value of D ≤ 1000 in minimal solutions of <i>x</i> for which the largest value of <i>x</i> is obtained.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem066 {

//    static List<Integer> factors = IntStream.iterate(2, val -> val <= 1000, i -> i + 1)
//            .filter(val -> !Problem064Cheated.isSquare(val))
//            .boxed()
//            .collect(Collectors.toList());
//
//    static List<Long> targets = LongStream.iterate(0, value -> value <= 10_000, value -> value + 1)
//            .map(value -> value * value)
//            .boxed()
//            .collect(Collectors.toList());

    static List<BigInteger> factorsBI = Stream.iterate(BigInteger.TWO,
            bi -> bi.compareTo(BigInteger.valueOf(1_000)) < 0, bi -> bi.add(BigInteger.ONE))
            .filter(bi -> !Problem064Cheated.isSquare(bi))
            .collect(Collectors.toList());

    static List<BigInteger> targetsBI = Stream.iterate(BigInteger.TWO,
            bi -> bi.compareTo(BigInteger.valueOf(100_000)) < 0, bi -> bi.add(BigInteger.ONE))
            .map(value -> value.multiply(value))
            .collect(Collectors.toList());

//    static Set<Long> targetSet = new HashSet<>(targets);

    static Set<BigInteger> targetSetBI = new HashSet<>(targetsBI);

//    public static void mapSolutions(int idx, Map<Integer, Long> mapping) {
//        for (int i = 0; i < idx; i++) {
//            Long targetMinusOne = targets.get(idx) - 1;
//            for (Integer actualFactor: factors) {
//                if (mapping.containsKey(actualFactor)) {
//                    continue;
//                }
//                if ((targetMinusOne) % actualFactor != 0) {
//                    continue;
//                }
//                long dividend = (targetMinusOne) / actualFactor;
//                if (dividend <= 0) {
//                    break;
//                }
//                if (targetSet.contains(dividend)) {
//                    mapping.put(actualFactor, targetMinusOne + 1);
//                }
//            }
//        }
//    }

    public static void mapSolutionsBI(int idx, Map<BigInteger, BigInteger> mapping) {
        for (int i = 0; i < idx; i++) {
            BigInteger targetMinusOne = targetsBI.get(idx).add(BigInteger.valueOf(-1)) ;
            for (BigInteger actualFactor: factorsBI) {
                if(mapping.size() == factorsBI.size()) {
                    return;
                }
                if (mapping.containsKey(actualFactor)) {
                    continue;
                }
                BigInteger[] parts = targetMinusOne.divideAndRemainder(actualFactor);
//                System.out.println(targetMinusOne + "/" + actualFactor);
//                System.out.println(parts[0] + "," + parts[1]);
                if (parts[1].compareTo(BigInteger.ZERO) != 0) {
                    continue;
                }
                BigInteger quotient = parts[0];
                if (quotient.signum() <= 0) {
                    break;
                }
                if (targetSetBI.contains(quotient)) {
                    BigInteger value = targetMinusOne.add(BigInteger.ONE);
                    mapping.put(actualFactor, value);
//                    System.out.println(String.format("mapping %d : %d", actualFactor, value));
                    System.out.println(String.format("mapping size %d ", mapping.size()));
                }
            }
        }
    }

    public static void main(String[] args) {
//        HashMap<Integer, Long> mapping = new HashMap<>();
//        for (int i = 0; i < 10_000; i++) {
//            mapSolutions(i, mapping);
//        }
//        System.out.println("size: " + mapping.size());
//        Long max = mapping.values().stream().max(Long::compare).orElse(-1L);
//        System.out.println("max: " + max);
//        mapping.entrySet().stream().filter(e -> e.getValue() == max)
//                .forEach(System.out::println);
//        size: 439
//        max: 96059601
//        29=96059601

//        System.out.println(factorsBI.size());
//        System.out.println(factorsBI.subList(0,100));
//        System.out.println(targetsBI.size());
//        System.out.println(targetsBI.subList(0,100));
        HashMap<BigInteger, BigInteger> mappingBI = new HashMap<>();
        System.out.println(String.format("factor count: %d", factorsBI.size()));
        for (int i = 0; i < targetsBI.size(); i++) {
            mapSolutionsBI(i, mappingBI);
        }
        System.out.println("size: " + mappingBI.size());
        BigInteger max = mappingBI.values().stream().max(BigInteger::compareTo).orElse(BigInteger.valueOf(-1));
        System.out.println("max: " + max);
        mappingBI.entrySet().stream().filter(e -> e.getValue() == max)
                .forEach(System.out::println);
    }
}
