import java.util.PriorityQueue;

// LC-264
// https://leetcode.com/problems/ugly-number-ii/

public class UglyNumberII {

  // Time  : O(NlogN)
  // Space : O(N)
  public int nthUglyNumber(int n) {
    PriorityQueue<Long> pq = new PriorityQueue<>();
    pq.add(1L);
    for (int i = 0; i < n - 1; i++) {
      long val = pq.remove();
      while (pq.size() > 0 && pq.peek() == val) {
        pq.remove(); // remove duplicates
      }
      pq.add(val * 2);
      pq.add(val * 3);
      pq.add(val * 5);
    }

    return pq.remove().intValue();
  }

  private final int LIMIT = 1690;

  // Time  : O(N)
  // Space : O(N)
  public int nthUglyNumberDP(int n) {
    // dp[i] denote the i + 1 ugly numbers
    int[] dp = new int[LIMIT];
    dp[0] = 1;
    // the smallest number that is not multiplied by 2, 3, 5
    int i2 = 0;
    int i3 = 0;
    int i5 = 0;
    for (int i = 1; i < LIMIT; i++) {
      dp[i] = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
      if (dp[i] == dp[i2] * 2) {
        i2++;
      }
      if (dp[i] == dp[i3] * 3) {
        i3++;
      }
      if (dp[i] == dp[i5] * 5) {
        i5++;
      }
    }

    return dp[n - 1];
  }
}
