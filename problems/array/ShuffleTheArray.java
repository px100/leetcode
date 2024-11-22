
// LC-1470
// https://leetcode.com/problems/shuffle-the-array/

public class ShuffleTheArray {

  public int[] shuffle(int[] nums, int n) {
    int[] result = new int[nums.length];
    int p = 0;
    for (int i = 0; i < n; i++) {
      result[p++] = nums[i];
      result[p++] = nums[i + n];
    }
    return result;
  }
}
