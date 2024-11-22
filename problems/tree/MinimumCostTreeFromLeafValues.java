
// LC-1130
// https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/

public class MinimumCostTreeFromLeafValues {

  public int mctFromLeafValues(int[] arr) {
    int n = arr.length;
    int[][] max = new int[n][n];
    for (int i = 0; i < n; i++) {
      max[i][i] = arr[i];
      for (int j = i + 1; j < n; j++) {
        max[i][j] = Math.max(arr[j], max[i][j - 1]);
      }
    }

    return helper(0, n - 1, new int[n][n], max);
  }

  private int helper(int start, int end, int[][] dp, int[][] max) {
    if (start >= end) {
      return 0;
    }
    if (dp[start][end] != 0) {
      return dp[start][end];
    }

    int result = Integer.MAX_VALUE;
    for (int i = start; i < end; i++) {
      result = Math.min(result, max[start][i] * max[i + 1][end]
        + helper(start, i, dp, max)
        + helper(i + 1, end, dp, max));
    }
    dp[start][end] = result;

    return result;
  }
}
