package euler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * </div>
 * <h2>Singular integer right triangles</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 75</h3>
 * <span>Published on Friday, 30th July 2004, 06:00 pm; Solved by 11355; Difficulty rating: 25%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>It turns out that 12 cm is the smallest length of wire that can be bent to form an integer sided right angle triangle in exactly one way, but there are many more examples.</p>
 * <p style="margin-left:50px;"><b>12 cm</b>: (3,4,5)<br><b>24 cm</b>: (6,8,10)<br><b>30 cm</b>: (5,12,13)<br><b>36 cm</b>: (9,12,15)<br><b>40 cm</b>: (8,15,17)<br><b>48 cm</b>: (12,16,20)</p>
 * <p>In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer sided right angle triangle, and other lengths allow more than one solution to be found; for example, using 120 cm it is possible to form exactly three different integer sided right angle triangles.</p>
 * <p style="margin-left:50px;"><b>120 cm</b>: (30,40,50), (20,48,52), (24,45,51)</p>
 * <p>Given that L is the length of the wire, for how many values of L ≤ 1,500,000 can exactly one integer sided right angle triangle be formed?</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem075 {

    static boolean numberOfRightAngleTriangles(int sum) {
        int minC = sum / 3 + 1;
        int maxC = sum - 3;
        int count = 0;
        for (int i = 3; i < minC; i++) {
            for (int j = i + 1; j <= maxC - (i + 1); j++) {
                int candidate = sum - (i + j);
                if (i * i + j * j == candidate * candidate) {
                    if (count == 1) {
                        return false;
                    }
                    count += 1;
                }
            }
        }
        return count == 1;
    }

    static Set<Long> findTriangleUsingSmallestSide(Long smallest) {
        int firstStep = smallest % 2 == 0 ? 4 : 2;
        return doFindTriangles(smallest, firstStep, new HashSet<>());
    }

    private static Set<Long> doFindTriangles(long smallest, long step, HashSet<Long> lengthFound) {
        double middle = (smallest * smallest * 1.0) / step;
        if (middle <= smallest) {
            return lengthFound;
        }
        lengthFound.add((long) (smallest + (middle + (step * 0.5) + (middle - (step * 0.5)))));
        return doFindTriangles(smallest, step + 4, lengthFound);
    }

    static Set<Long> assembleSets(Set<Long> untilNow, Set<Long> justFound) {
        HashSet<Long> copy = new HashSet<>(untilNow);
        untilNow.removeAll(justFound);
        justFound.removeAll(copy);
        untilNow.addAll(justFound);
        return untilNow;
    }

    static Map<Long, Integer> findTriangleUsingSmallestSideIterative(Map<Long, Integer> found2count, Long smallest) {
        int firstStep = smallest % 2 == 0 ? 4 : 2;
        doFindTriangles(found2count, smallest, firstStep);
        return found2count;
    }

    private static void doFindTriangles(Map<Long, Integer> found2count, long smallest, long step) {
        double square = smallest * smallest * 1.0;
        double middle = square / step;
        while (middle - (step * 0.25) > smallest) {
            double largest = middle + (step * 0.25);
            double inBetween = middle - (step * 0.25);
            if(largest % 0.5 == 0 && inBetween % 0.5 == 0) {
//                System.out.println(
//                        String.format("smallest, inbetween, largest: %d, %.0f, %.0f", smallest, inBetween, largest));
                long newLength = (long) (smallest + inBetween + largest);
                found2count.putIfAbsent(newLength, 0);
                found2count.compute(newLength, (k, v) -> v + 1);
            }
            step += 4;
            middle = square / step;
        }
    }

    public static void main(String[] args) {
//        LongStream.range(3, 500_000).boxed()
//                .peek((aLong) -> {
//                    if (aLong % 10_000 == 0) {
//                        System.out.println(aLong);
//                    }
//                })
//                .map(Problem075::findTriangleUsingSmallestSide)
//                .reduce(Problem075::assembleSets)
//                .map(Collection::size)
//        .ifPresent(System.out::println);

        HashMap<Long, Integer> counter = new HashMap<>();
        for (long i = 3; i < 500_000; i++) {
            if (i % 100_000 == 0) {
                System.out.println(i);
            }
            findTriangleUsingSmallestSideIterative(counter, i);
        }
//        findTriangleUsingSmallestSideIterative(counter,6L);
//        findTriangleUsingSmallestSideIterative(counter,7L);
//        findTriangleUsingSmallestSideIterative(counter,8L);

//        System.out.println(counter);
        System.out.println(counter.size());
        System.out.println(counter.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .count());


    }


}
