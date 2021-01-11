package euler;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.math.BigInteger;

/**
*</div> 
<h2>Square root digital expansion</h2>
<div id="problem_icons" class="noprint">
 <a href="minimal=80"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 8th October 2004, 06:00 pm; Solved by 19269;<br>Difficulty rating: 20%</span></span>
</div>
<div id="problem_info">
 <h3>Problem 80</h3>
</div> 
<div class="problem_content" role="problem"> 
 <p>It is well known that if the square root of a natural number is not an integer, then it is irrational. The decimal expansion of such square roots is infinite without any repeating pattern at all.</p> 
 <p>The square root of two is 1.41421356237309504880..., and the digital sum of the first one hundred decimal digits is 475.</p> 
 <p>For the first one hundred natural numbers, find the total of the digital sums of the first one hundred decimal digits for all the irrational square roots.</p> 
</div>
<br> 
<br>
*/
public class Problem080 {


    /*Returns the square root of n.
    Note that the function */
    static float squareRoot(float n)
    {

        /*We are using n itself as
        initial approximation This
        can definitely be improved */
        float x = n;
        float y = 1;

        // e decides the accuracy level
        double e = 0.000000000000000001;
        while (x - y > e) {
            x = (x + y) / 2;
            y = n / x;
        }
        return x;
    }


    public static void main(String[] args) {
//    System.out.println(Math.sqrt(2.0));
    String sqrt_99 = "9.94987437106619954734479821001206005178" +
            "1265636768060791176046438349453927827131" +
            "54012653019738487195";
    System.out.println(sqrt_99.substring(2).length());

    int n = 99;

    System.out.printf("Square root of "
            + n + " is " + squareRoot(n));
}

}
