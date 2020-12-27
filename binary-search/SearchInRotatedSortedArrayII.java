
// LC-81
// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

public class SearchInRotatedSortedArrayII {

  // TC: O(log(n))
  public boolean search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return false;
    }

    int i = 0;
    int j = nums.length - 1;
    while (i <= j) {
      int m = i + (j - i) / 2;
      if (nums[m] == target) {
        return true;
      }
      if (nums[m] == nums[i]) { // duplicate; skip
        i++;
        // 'bigger' increasing side, like [5,5,7] from [5,5,7,0,1,3,4]
      } else if (nums[m] > nums[i]) {
        if (nums[m] > target && nums[i] <= target) {
          // if target is between nums[i] and nums[m], set the right border to m-1
          j = m - 1;
        } else {
          // if target is NOT between nums[i] and nums[m] then target is on the 'right' side of m;
          // limit the 'left' border [i=m+1]
          i = m + 1;
        }
        // 'smaller' increasing side, like [0,1,3,4] from [5,5,7,0,1,3,4]
      } else {
        if (nums[m] < target && nums[j] >= target) {
          // if target is between nums[m] and nums[j], it makes sense to set the left border to m+1
          i = m + 1;
        } else {
          // if target is NOT between nums[m] and nums[j] then target is on the 'left' side of m;
          // limit the 'right' border [j=m-1]
          j = m - 1;
        }
      }
    }
    return false;
  }
}
