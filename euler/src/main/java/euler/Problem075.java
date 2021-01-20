package euler;

import java.util.*;

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
                double sum = smallest + inBetween + largest;
                System.out.println(
                        String.format("smallest, inbetween, largest: %8d, %8.0f, %8.0f = %8.0f", smallest, inBetween, largest, sum));
                long newLength = (long) sum;
                found2count.putIfAbsent(newLength, 0);
                found2count.compute(newLength, (k, v) -> v + 1);
            }
            step += 4;
            middle = square / step;
        }
    }

    public static void main(String[] args) {
        Map<Long, Integer> counter = new TreeMap<>();
        for (long i = 3; i < 101; i++) {
            if (i % 100_000 == 0) {
                System.out.println(i);
            }
            findTriangleUsingSmallestSideIterative(counter, i);
        }
        System.out.println(counter);
    }
}
