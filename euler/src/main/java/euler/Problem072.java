package euler;

import utils.Utils;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
*</div> 
<h2>Counting fractions</h2>
<div id="problem_info" class="info">
 <h3>Problem 72</h3>
 <span>Published on Friday, 18th June 2004, 06:00 pm; Solved by 14285; Difficulty rating: 20%</span>
</div> 
<div class="problem_content" role="problem"> 
 <p>Consider the fraction, <i>n/d</i>, where <i>n</i> and <i>d</i> are positive integers. If <i>n</i>&lt;<i>d</i> and HCF(<i>n,d</i>)=1, it is called a reduced proper fraction.</p> 
 <p>If we list the set of reduced proper fractions for <i>d</i> ≤ 8 in ascending order of size, we get:</p> 
 <p style="text-align:center;font-size:90%;">1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8</p> 
 <p>It can be seen that there are 21 elements in this set.</p> 
 <p>How many elements would be contained in the set of reduced proper fractions for <i>d</i> ≤ 1,000,000?</p> 
</div>
<br> 
<br>
*/
public class Problem072 {

public static void main(String[] args) {
    System.out.println(LongStream.rangeClosed(2, 1_000_000).boxed()
            .mapToLong(Utils::phi)
            .sum());


}

}
