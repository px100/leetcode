
// LC-474
// Medium
// https://leetcode.com/problems/ones-and-zeroes/

public class OnesAndZeroes {

  // 0/1 Knapsack
  public int findMaxForm(String[] strs, int m, int n) {
    if (strs == null || strs.length == 0 || m < 0 || n < 0) {
      return 0;
    }
    int[][] dp = new int[m + 1][n + 1];
    for (String str : strs) {
      int ones = 0;
      int size = str.length();
      for (int k = 0; k < size; k++) {
        if (str.charAt(k) == '1') {
          ones++;
        }
      }
      int zeroes = size - ones;
      for (int i = m; i >= zeroes; i--) {
        for (int j = n; j >= ones; j--) {
          dp[i][j] = Math.max(dp[i][j], dp[i - zeroes][j - ones] + 1);
        }
      }
    }
    return dp[m][n];
  }
}
