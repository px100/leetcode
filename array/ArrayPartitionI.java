import java.util.Arrays;

// LC-561
// https://leetcode.com/problems/array-partition-i/

public class ArrayPartitionI {

  // O(N*log(N))
  public int arrayPairSum(int[] nums) {
    Arrays.sort(nums);
    int sum = 0;
    for (int i = 0; i < nums.length; i += 2) {
      sum += nums[i];
    }

    return sum;
  }

  // O(N)
  public int arrayPairSum2(int[] nums) {
    int[] arr = new int[20000 + 1];
    int lim = 10000;
    for (int num : nums) {
      arr[num + lim]++;
    }

    int d = 0;
    int sum = 0;
    for (int i = -10000; i <= 10000; i++) {
      sum += ((arr[i + lim] + 1 - d) >> 1) * i;
      d = (arr[i + lim] + 2 - d) & 1;
    }

    return sum;
  }
}
