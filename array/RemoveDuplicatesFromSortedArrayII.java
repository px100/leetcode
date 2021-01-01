
// LC-80
// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/

public class RemoveDuplicatesFromSortedArrayII {

  public int removeDuplicates(int[] nums) {
    int size = nums.length;
    if (size < 3) {
      return size;
    }

    int i = 1;
    for (int j = 2; j < size; j++) {
      if (nums[j] != nums[i] || nums[j] != nums[i - 1]) {
        nums[++i] = nums[j];
      }
    }
    return i + 1;
  }
}
