public class FindTheNumberOfSubsequencesWithEqualGCD {

  private static final int MOD = 1_000_000_007;

  public int subsequencePairCount(int[] nums) {
    int[][] dp = new int[201][201];
    dp[0][0] = 1;
    for (int num : nums) {
      int[][] res = new int[201][201];
      for (int i = 0; i <= 200; i++) {
        for (int j = 0; j <= 200; j++) {
          if (dp[i][j] != 0) {
            int k = dp[i][j];
            res[i][j] = (res[i][j] + k) % MOD;
            int ni = (i == 0) ? num : gcd(i, num);
            res[ni][j] = (res[ni][j] + k) % MOD;
            int nj = (j == 0) ? num : gcd(j, num);
            res[i][nj] = (res[i][nj] + k) % MOD;
          }
        }
      }
      dp = res;
    }
    long ans = 0;
    for (int i = 1; i <= 200; i++) {
      ans = (ans + dp[i][i]) % MOD;
    }
    return (int) ans;
  }

  private int gcd(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }
}
