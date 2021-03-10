package euler;

import utils.Combinations;
import utils.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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

    public static void adjustForNumber(Map<Long, Long> minima, NumberFactors target) {
        long productAccumulated = 1;
        long sumAccumulated = 0;
        for (int i = 0; i < target.factors.size() - 1; i++) {
            productAccumulated *= target.factors.get(i);
            sumAccumulated += target.factors.get(i);
            int usedFactors = i + 2;
            long targetCount = target.number - (target.number / productAccumulated + sumAccumulated) + usedFactors;
            minima.putIfAbsent(targetCount, target.number);
            minima.compute(targetCount, (k, v) -> Long.min(v, target.number));
        }
    }

    public static void adjustForNumberUsingCombinations(Map<Long, Long> minima, NumberFactors target) {
//        int maxFactorCount = (target.factors.size() - 1) / 2 + (target.factors.size() - 1) % 2 == 0 ? 0 : 1;
        int maxFactorCount = (target.factors.size() - 1);
        for (int i = 1; i <= maxFactorCount; i++) {
            List<List<Long>> combis = Combinations.combinations(i, target.factors);
            for (List<Long> combi : combis) {
                Long product = combi.stream().reduce(1L, (l1, l2) -> l1 * l2);
                Long sum = combi.stream().reduce(0L, (l1, l2) -> l1 + l2);
                long targetCount = target.number - (target.number / product + sum) + combi.size() + 1;
                minima.putIfAbsent(targetCount, target.number);
                minima.compute(targetCount, (k, v) -> Long.min(v, target.number));
            }
        }
    }

    public static void adjustForNumberUsingPartitions(Map<Long, Long> minima, NumberFactors target) {
        int size = target.factors.size();
        if(size >= 13) {
            System.out.println("giving up...");
            System.out.println(String.format("target: %s", target));
            System.out.println();
            return;
        }
        List<String> partitions = Combinations.partitions(size).get(size);
        for (String partition : partitions.subList(0, partitions.size() - 1)) {
            String[] sets = partition.split(Combinations.SET_SEPARATOR);
            long sum = Arrays.stream(sets).
                    mapToLong(set -> Arrays.stream(set.split(Combinations.SEPARATOR))
                            .peek(str -> {
                                if (Integer.valueOf(str) > size) {
                                    System.out.println(set);
                                    System.out.println(partitions.size());
                                }
                            })
                            .mapToLong(str -> target.factors.get(Integer.valueOf(str)))
                            .reduce(1L, (l1, l2) -> l1 * l2))
                    .reduce(0L, (l1, l2) -> l1 + l2);
            long targetCount = target.number - sum + sets.length;
            if (targetCount <= 12_000) {
                minima.putIfAbsent(targetCount, target.number);
                if (minima.size() == 12_000 - 1) {
                    return;
                }
            }
        }
    }


    private static void printSummedValues(Map<Long, Long> minima, long maxSetSize) {
        Long summed = minima.entrySet().stream().filter(e -> e.getKey() <= maxSetSize)
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet())
                .stream()
                .peek(System.out::println)
                .reduce(Long::sum)
                .orElse(-1L);
        System.out.println(String.format("max set length: %d, summed: %d", maxSetSize, summed));
    }

    public static void main(String[] args) {
        int maxLength = 12_096;
        Map<Long, Long> minima = new HashMap<>();
        LongStream.rangeClosed(4, maxLength)
                .filter(l -> !Utils.isPrime(l))
                .boxed()
                .map(i -> new NumberFactors(i, Utils.primeFactors(i).boxed().collect(Collectors.toList())))
                .forEach(nf -> adjustForNumberUsingPartitions(minima, nf));
        System.out.println(minima);
        System.out.println("size: " + minima.size());
//        LongStream.of(6, 12, 100).
        LongStream.of(12_000).
                forEach(ml -> printSummedValues(minima, ml));

//        System.out.println(LongStream.rangeClosed(4, 24_000)
//                .filter(l -> !Utils.isPrime(l))
//                .boxed()
//                .peek(System.out::println)
//                .map(i ->  Utils.primeFactors(i).boxed().collect(Collectors.toList()))
//                .max(Comparator.comparingInt(List::size))
//                .orElse(Collections.emptyList()));
// 2 ** 14 , Bell number: 14: 190_899_322
//           Bell number: 13:  27_644_437
//        System.out.println(Utils.primeFactors(12096L).boxed().collect(Collectors.toList()));
    }
}
