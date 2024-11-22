import java.util.Arrays;

// LC-719
// https://leetcode.com/problems/find-k-th-smallest-pair-distance/

public class FindKthSmallestPairDistance {

  // O(N*log(N))
  public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums);
    int size = nums.length;

    int left = 0;
    int right = nums[size - 1] - nums[0];
    while (left < right) {
      int mid = left + (right - left) / 2;
      int j = 0;
      int count = 0;
      for (int i = 0; i < size; i++) {
        while (j < size && nums[j] - nums[i] <= mid) {
          j++;
        }
        count += j - i - 1;
      }

      if (count < k) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }

  // O(N^2)
  public int smallestDistancePairBucketSort(int[] nums, int k) {
    int n = nums.length, N = 1_000_000;
    int[] bucket = new int[N];

    // for all pairs, put diff in corresponding bucket
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        bucket[Math.abs(nums[i] - nums[j])]++;
      }
    }
    // find 1st bucket with count >= k
    // return it's index (distance)
    for (int i = 0; i < N; i++) {
      if (bucket[i] < k) {
        k -= bucket[i];
      } else {
        return i;
      }
    }

    return -1;
  }
}
