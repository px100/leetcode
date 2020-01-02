import java.util.Arrays;

// LC-698
// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

public class PartitionToKEqualSumSubsets {

  public boolean canPartitionKSubsetsDpBitmasking(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return false;
    }

    int n = nums.length;
    boolean[] dp = new boolean[1 << n];
    dp[0] = true;

    // stores the sum of subset with sum less than equal to target sum
    int[] total = new int[1 << n];

    int sum = Arrays.stream(nums).sum();
    Arrays.sort(nums);

    if (sum % k != 0) {
      return false;
    }
    sum /= k;
    if (nums[n - 1] > sum) {
      return false;
    }
    // iterate over power set
    for (int i = 0; i < (1 << n); i++) {
      if (dp[i]) {
        // iterate over each element to find subset
        for (int j = 0; j < n; j++) {
          // set the jth bit
          int temp = i | (1 << j);
          if (temp != i) {
            // if total sum is less than target store in dp and total array
            if (nums[j] <= (sum - (total[i] % sum))) {
              dp[temp] = true;
              total[temp] = nums[j] + total[i];
            } else {
              break;
            }
          }
        }
      }
    }

    return dp[(1 << n) - 1];
  }

  public boolean canPartitionKSubsets(int[] nums, int k) {
    int n = nums.length;
    if (n < k) {
      return false;
    }

    int total = Arrays.stream(nums).sum();
    if (total % k != 0) {
      return false; // Cannot be divided by k, then return false
    }

    int sum = total / k; // The sum of every single subarray
    boolean[] visited = new boolean[n];

    return dfs(nums, 0, visited, k, sum, sum);
  }

  private boolean dfs(int[] nums, int start, boolean[] visited, int k, int rem, int sum) {
    if (rem < 0) {
      return false;
    }
    if (rem == 0) {
      --k;
      rem = sum;
      start = 0; // Restart from index 0
    }
    if (k == 0) {
      return true;
    }

    int n = visited.length;
    for (int i = start; i < n; i++) {
      if (!visited[i]) {
        visited[i] = true;
        if (dfs(nums, i, visited, k, rem - nums[i], sum)) {
          return true;
        }
        visited[i] = false;
      }
    }

    return false;
  }
}
