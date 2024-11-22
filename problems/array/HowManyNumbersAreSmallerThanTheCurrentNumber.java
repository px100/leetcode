
// LC-1365
// https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HowManyNumbersAreSmallerThanTheCurrentNumber {

  public int[] smallerNumbersThanCurrent(int[] nums) {
    int n = nums.length;
    int[] copy = Arrays.copyOf(nums, n);
    Arrays.sort(copy);

    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      map.putIfAbsent(copy[i], i);
    }

    for (int i = 0; i < n; i++) {
      nums[i] = map.get(nums[i]);
    }
    return nums;
  }
}
