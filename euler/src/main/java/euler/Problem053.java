package euler;

/**
*</div> 
<h2>Combinatoric selections</h2>
<div id="problem_info" class="info">
 <h3>Problem 53</h3>
 <span>Published on Friday, 26th September 2003, 06:00 pm; Solved by 39107; Difficulty rating: 5%</span>
</div> 
<div class="problem_content" role="problem"> 
 <p>There are exactly ten ways of selecting three from five, 12345:</p> 
 <p style="text-align:center;">123, 124, 125, 134, 135, 145, 234, 235, 245, and 345</p> 
 <p>In combinatorics, we use the notation, <sup>5</sup>C<sub>3</sub> = 10.</p> 
 <p>In general,</p> 
 <div style="text-align:center;"> 
  <table>
   <tbody>
    <tr>
     <td><sup><var>n</var></sup>C<sub><var>r</var></sub> = </td> 
     <td>
      <div style="text-align:center;">
       <var>n</var>!
       <br>
       <span style="border-top:1px solid #000;"><var>r</var>!(<var>n−r</var>)!</span>
      </div></td> 
     <td>,where <var>r</var> ≤ <var>n</var>, <var>n</var>! = <var>n</var>×(<var>n</var>−1)×...×3×2×1, and 0! = 1.</td> 
    </tr>
   </tbody>
  </table>
 </div> 
 <p>It is not until <var>n</var> = 23, that a value exceeds one-million: <sup>23</sup>C<sub>10</sub> = 1144066.</p> 
 <p>How many, not necessarily distinct, values of &nbsp;<sup><var>n</var></sup>C<sub><var>r</var></sub>, for 1 ≤ <var>n</var> ≤ 100, are greater than one-million?</p> 
</div>
<br> 
<br>
*/
public class Problem053 {

public static void main(String[] args) {

}

}
