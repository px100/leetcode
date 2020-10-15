
// LC-375
// https://leetcode.com/problems/guess-number-higher-or-lower-ii/

public class GuessNumberHigherOrLowerII {

  private int[][] dp;

  public int getMoneyAmount(int n) {
    dp = new int[n + 1][n + 1];
    return helper(1, n);
  }

  private int helper(int start, int end) {
    if (dp[start][end] != 0) {
      return dp[start][end];
    }
    if (start >= end) {
      return 0;
    }
    // if the range is less or equal to 3 (start >= end - 2),
    // the cost will be the upper boundary minus 1 (end - 1).
    if (start >= end - 2) {
      return dp[start][end] = end - 1;
    }

    int mid = (start + end) / 2 - 1;
    int min = Integer.MAX_VALUE;
    while (mid < end) {
      int left = helper(start, mid - 1);
      int right = helper(mid + 1, end);
      min = Math.min(min, mid + Math.max(left, right));
      if (left >= right) {
        break;
      }
      mid++;
    }

    return dp[start][end] = min;
  }
}
