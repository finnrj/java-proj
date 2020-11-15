package euler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * </div>
 * <h2>Cubic permutations</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 62</h3>
 * <span>Published on Friday, 30th January 2004, 06:00 pm; Solved by 19704; Difficulty rating: 15%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>The cube, 41063625 (345<sup>3</sup>), can be permuted to produce two other cubes: 56623104 (384<sup>3</sup>) and 66430125 (405<sup>3</sup>). In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.</p>
 * <p>Find the smallest cube for which exactly five permutations of its digits are cube.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem062 {

    public static String tag(long target) {
        return String.valueOf(target).chars().sorted().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        LongUnaryOperator cubes = n -> n * n * n;
        LongStream.range(5000L, 8500L).map(cubes)
                .boxed().collect(Collectors.groupingBy(Problem062::tag))
                .entrySet().stream().filter(e -> e.getValue().size() >= 5)
//                .flatMap(e -> e.getValue().stream().map(d -> Math.pow(d, 0.3333333333333)))
        .forEach(System.out::println);

    }

}
