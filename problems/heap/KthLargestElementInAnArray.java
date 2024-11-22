import java.util.PriorityQueue;

// LC-215
// https://leetcode.com/problems/kth-largest-element-in-an-array/

public class KthLargestElementInAnArray {

  public int findKthLargest(int[] nums, int k) {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int num : nums) {
      minHeap.add(num);
    }

    while (minHeap.size() > k) {
      minHeap.remove();
    }

    return minHeap.remove();
  }

  public int findKthLargestPivot(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    return part(nums, nums.length - k, 0, nums.length - 1);
  }

  private int part(int[] nums, int k, int left, int right) {
    if (left >= right) {
      return nums[k];
    }

    int l = left;
    int r = right;
    int p = nums[l + (r - l) / 2];

    while (l <= r) {
      while (l <= r && nums[l] < p) {
        l++;
      }
      while (l <= r && nums[r] > p) {
        r--;
      }
      if (l <= r) {
        swap(nums, l++, r--);
      }
    }
    if (k <= r) {
      return part(nums, k, left, r);
    }
    if (k >= l) {
      return part(nums, k, l, right);
    }

    return nums[k];
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
