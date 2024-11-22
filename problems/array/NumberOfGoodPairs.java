
// LC-1512
// https://leetcode.com/problems/number-of-good-pairs/

import java.util.Arrays;
import java.util.stream.Collectors;

public class NumberOfGoodPairs {

  // TC: O(n*log(n))
  // SC: O(1)
  public int numIdenticalPairs(int[] nums) {
    Arrays.sort(nums);

    int last = nums[0];
    int count = 1;
    int ans = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == last) {
        count++;
      } else {
        if (count > 1) {
          ans += count * (count - 1) / 2;
        }
        last = nums[i];
        count = 1;
      }
    }
    if (count > 1) {
      ans += count * (count - 1) / 2;
    }
    return ans;
  }

  // TC: O(n)
  // SC: O(n)
  public int numIdenticalPairs2(int[] nums) {
    return Arrays.stream(nums)
        .boxed()
        .collect(Collectors.toMap(num -> num, num -> 1, Integer::sum))
        .values()
        .stream()
        .mapToInt(freq -> freq * (freq - 1) / 2)
        .sum();
  }

  // TC: O(n^2)
  // SC: O(1)
  public int numIdenticalPairs3(int[] nums) {
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] == nums[j]) {
          count++;
        }
      }
    }
    return count;
  }
}
