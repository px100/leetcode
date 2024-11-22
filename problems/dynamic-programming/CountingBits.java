
// LC-338
// https://leetcode.com/problems/counting-bits

public class CountingBits {

  public int[] countBits(int num) {
    int[] res = new int[num + 1];
    for (int i = 0; i <= num; i++) {
      res[i] = Integer.bitCount(i);
    }
    return res;
  }

  public int[] countBitsDp(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 0;
    for (int i = 1; i <= n; i++) {
      dp[i] = dp[i / 2] + (i % 2);
    }
    return dp;
  }
}
