
// LC-1043
// https://leetcode.com/problems/partition-array-for-maximum-sum/

public class PartitionArrayForMaximumSum {

  public int maxSumAfterPartitioning(int[] A, int K) {
    return dfs(A, K, 0, new int[A.length]);
  }

  private int dfs(int[] A, int K, int start, int[] dp) {
    int n = A.length;
    if (start >= n) {
      return 0;
    }
    if (dp[start] != 0) {
      return dp[start];
    }

    int maxSum = 0;
    int maxEle = 0;
    for (int i = start; i < Math.min(n, start + K); i++) {
      maxEle = Math.max(maxEle, A[i]);
      maxSum = Math.max(maxSum, maxEle * (i - start + 1) + dfs(A, K, i + 1, dp));
    }
    dp[start] = maxSum;

    return maxSum;
  }

  public int maxSumAfterPartitioning2(int[] A, int K) {
    int n = A.length;
    int[] dp = new int[n];

    int max = A[0];
    for (int i = 0; i < K; i++) {
      max = Math.max(max, A[i]);
      dp[i] = max * (i + 1);
    }

    for (int i = K; i < n; i++) {
      max = A[i];
      for (int j = 1; j <= K; j++) {
        max = Math.max(max, A[i - j + 1]);
        dp[i] = Math.max(dp[i], dp[i - j] + max * j);
      }
    }

    return dp[n - 1];
  }
}
