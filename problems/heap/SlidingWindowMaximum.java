import java.util.ArrayDeque;
import java.util.Deque;

// LC-239
// https://leetcode.com/problems/sliding-window-maximum/
// https://leetcode.com/problems/sliding-window-maximum/discuss/458121/Java-All-Solutions-(B-F-PQ-Deque-DP)-with-Explanation-and-Complexity-Analysis

public class SlidingWindowMaximum {

  // Time  : O(N)
  // Space : O(k)
  // For each element nums[i] in the array that we are about to insert,
  // we first check whether the leftmost index in the sliding window is out of bound.
  // If so, we remove it by pollFirst() in constant time.
  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    if (n == 0 || k == 0) {
      return new int[0];
    }

    int[] result = new int[n - k + 1]; // number of windows
    Deque<Integer> window = new ArrayDeque<>(); // stores indices

    for (int i = 0; i < n; i++) {
      // remove indices that are out of bound
      while (window.size() > 0 && window.peekFirst() <= i - k) {
        window.pollFirst();
      }
      // remove indices whose corresponding values are less than nums[i]
      while (window.size() > 0 && nums[window.peekLast()] < nums[i]) {
        window.pollLast();
      }
      // add nums[i]
      window.offerLast(i);
      // add to result
      if (i >= k - 1) {
        result[i - k + 1] = nums[window.peekFirst()];
      }
    }

    return result;
  }

  // Time  : O(N)
  // Space : O(N)
  public int[] maxSlidingWindowN(int[] nums, int k) {
    if (nums.length == 0 || k == 0) {
      return new int[0];
    }

    int n = nums.length;
    int[] result = new int[n - k + 1]; // number of windows

    // left & right
    int[] left = new int[n];
    int[] right = new int[n];

    left[0] = nums[0]; // init
    right[n - 1] = nums[n - 1];

    for (int i = 1; i < n; i++) {
      // left
      left[i] = i % k == 0 ? nums[i] : Math.max(left[i - 1], nums[i]);
      // right
      int j = n - i - 1;
      right[j] = j % k == (k - 1) ? nums[j] : Math.max(right[j + 1], nums[j]);
    }

    for (int i = 0, j = i + k - 1; j < n; i++, j++) {
      result[i] = Math.max(right[i], left[j]);
    }

    return result;
  }
}
