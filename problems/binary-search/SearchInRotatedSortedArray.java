
// LC-33
// https://leetcode.com/problems/search-in-rotated-sorted-array/

public class SearchInRotatedSortedArray {

  // TC: O(log(n))
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int i = 0;
    int j = nums.length - 1;
    while (i <= j) {
      int m = i + (j - i) / 2;
      if (nums[m] == target) {
        return m;
      }
      if (nums[m] >= nums[i]) {
        if (nums[m] > target && nums[i] <= target) {
          j = m - 1;
        } else {
          i = m + 1;
        }
      } else {
        if (nums[m] < target && nums[j] >= target) {
          i = m + 1;
        } else {
          j = m - 1;
        }
      }
    }
    return -1;
  }
}
