package euler;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    record Measure(long length, long width, long height) {
    }

    public static final int MAX_LENGTH = 1_500_000;

    static Map<Long, List<Measure>> findTriangleUsingSmallestSideIterative(Map<Long, List<Measure>> found2count, Long smallest) {
        int firstStep = smallest % 2 == 0 ? 4 : 2;
        doFindTriangles(found2count, smallest, firstStep);
        return found2count;
    }

    private static void doFindTriangles(Map<Long, List<Measure>> found2count, long smallest, long step) {
        double square = smallest * smallest * 1.0;
        double middle = square / step;
        while (middle - (step * 0.25) > smallest) {
            double largest = middle + (step * 0.25);
            double inBetween = middle - (step * 0.25);
            long largestLong = (long) largest;
            long inBetweenLong = (long) inBetween;
            long newLength = smallest + inBetweenLong + largestLong;
            if (newLength <= MAX_LENGTH && (!(largest > largestLong || inBetween > inBetweenLong))) {
//                System.out.println(
//                        String.format("smallest, inbetween, largest: %8d, %8.0f, %8.0f = %8d", smallest, inBetween, largest, newLength));
                found2count.putIfAbsent(newLength, new ArrayList<>());
                found2count.compute(newLength, (k, v) -> {
                    v.add(new Measure(smallest, (long) inBetween, (long) largest));
                    return v;
                });
            }
            step += 4;
            middle = square / step;
        }
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        Map<Long, List<Measure>> counter = new TreeMap<>();
        int maxShortestSide = MAX_LENGTH / 3;
//        int maxShortestSide = 100;
        for (long i = 3; i < maxShortestSide; i++) {
            if (i % 100_000 == 0) {
                System.out.println(i);
            }
            findTriangleUsingSmallestSideIterative(counter, i);
        }
//        for (Map.Entry<Long, List<Measure>> entry: counter.entrySet()) {
//            System.out.println(String.format("%4d: %s", entry.getKey(), entry.getValue()));
//        }
        System.out.println(counter.size());
        System.out.println("singular right triangles:" + counter.values().stream().filter(list -> list.size() == 1).count());
//        System.out.println(counter);
//        System.out.println("shortest path is: " + shortestMatrixPath(result));
        System.out.println("it took " + Duration.between(start, Instant.now()));
// singular right triangles:161667
//it took PT3M37.718629S
    }
}
