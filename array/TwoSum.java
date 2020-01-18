import java.util.HashMap;
import java.util.Map;

// LC-01
// https://leetcode.com/problems/two-sum/

public class TwoSum {

  public int[] twoSum(int[] nums, int target) {
    final int MOD = 2047;
    int[] map = new int[2048];
    for (int i = 0; i < nums.length; i++) {
      int key = target - nums[i] & MOD;
      if (map[key] != 0) {
        return new int[]{map[key] - 1, i};
      }
      map[nums[i] & MOD] = i + 1;
    }

    return new int[]{};
  }

  public int[] twoSumSlow(int[] nums, int target) {
    int[] result = new int[2];
    if (nums == null || nums.length == 0) {
      return result;
    }

    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (!map.containsKey(target - nums[i])) {
        map.put(nums[i], i);
      } else {
        result[0] = map.get(target - nums[i]);
        result[1] = i;
        break;
      }
    }

    return result;
  }
}
