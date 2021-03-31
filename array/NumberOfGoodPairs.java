
// LC-1512
// https://leetcode.com/problems/number-of-good-pairs/

import java.util.Arrays;
import java.util.stream.Collectors;

public class NumberOfGoodPairs {

  public int numIdenticalPairs(int[] nums) {
    return Arrays.stream(nums)
        .boxed()
        .collect(Collectors.toMap(num -> num, num -> 1, Integer::sum))
        .values()
        .stream()
        .mapToInt(freq -> freq * (freq - 1) / 2)
        .sum();
  }
}
