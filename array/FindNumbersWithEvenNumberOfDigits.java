import java.util.Arrays;

// LC-1295
// https://leetcode.com/problems/find-numbers-with-even-number-of-digits/

public class FindNumbersWithEvenNumberOfDigits {

  public int findNumbers(int[] nums) {
    return (int) Arrays.stream(nums).filter(i -> (((int) Math.log10(i) + 1) & 1) == 0).count();
  }
}
