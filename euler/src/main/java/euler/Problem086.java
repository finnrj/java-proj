package euler;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * </div>
 * <h2>Cuboid route</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=86"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 7th January 2005, 06:00 pm; Solved by 12235;<br>Difficulty rating: 35%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 86</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>A spider, S, sits in one corner of a cuboid room, measuring 6 by 5 by 3, and a fly, F, sits in the opposite corner. By travelling on the surfaces of the room the shortest "straight line" distance from S to F is 10 and the path is shown on the diagram.</p>
 * <div class="center">
 * <img src="project/images/p086.png" class="dark_img" alt="">
 * <br>
 * </div>
 * <p>However, there are up to three "shortest" path candidates for any given cuboid and the shortest route doesn't always have integer length.</p>
 * <p>It can be shown that there are exactly 2060 distinct cuboids, ignoring rotations, with integer dimensions, up to a maximum size of M by M by M, for which the shortest route has integer length when M = 100. This is the least value of M for which the number of solutions first exceeds two thousand; the number of solutions when M = 99 is 1975.</p>
 * <p>Find the least value of M such that the number of solutions first exceeds one million.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem086 {

    public static final int MAX_SHORTEST_SIDE = 100;

    record Measure(long length, long width, long height) {
    }

    static List<Measure> findTriangleUsingSmallestSideIterative(List<Measure> found2count, Long smallest) {
        int firstStep = smallest % 2 == 0 ? 4 : 2;
        doFindTriangles(found2count, smallest, firstStep);
        return found2count;
    }

    private static void doFindTriangles(List<Measure> foundCuboid, long smallest, long step) {
        double square = smallest * smallest * 1.0;
        double middle = square / step;
        while (middle - (step * 0.25) > smallest) {
            double largest = middle + (step * 0.25);
            double inBetween = middle - (step * 0.25);
            long largestLong = (long) largest;
            long inBetweenLong = (long) inBetween;
            if (inBetweenLong <= 2 * MAX_SHORTEST_SIDE
                    && (!(largest > largestLong || inBetween > inBetweenLong))) {
                for (long i = Math.max(inBetweenLong - 100, 1); i <= inBetweenLong / 2; i++) {
                    foundCuboid.add(new Measure(smallest, i, inBetweenLong - i));
                    System.out.println(
                            String.format("length, width, height: %8d, %8d, %8d", smallest, i, inBetweenLong - i));
                }
            }
            step += 4;
            middle = square / step;
        }
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        List<Measure> counter = new ArrayList<>();
        for (long i = 3; i <= MAX_SHORTEST_SIDE; i++) {
//            if (i % 100_000 == 0) {
//                System.out.println(i);
//            }
            findTriangleUsingSmallestSideIterative(counter, i);
        }
        System.out.println(counter.size());
//        System.out.println("singular right triangles:" + counter.values().stream().filter(integer -> integer == 1).count());
//        System.out.println(counter);
//        System.out.println("shortest path is: " + shortestMatrixPath(result));
        System.out.println("it took " + Duration.between(start, Instant.now()));
        counter.stream().filter(measure -> measure.length() == measure.width() && measure.width() == measure.height())
                .forEach(System.out::println);
    }
}
