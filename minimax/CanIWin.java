
// LC-464
// https://leetcode.com/problems/can-i-win/

public class CanIWin {

  // time : O(2^m * m)
  // space : O(2^m)
  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    if (desiredTotal <= 0) {
      return true;
    }
    if ((maxChoosableInteger * (maxChoosableInteger + 1) / 2) < desiredTotal) {
      return false;
    }

    int[] dp = new int[1 << maxChoosableInteger];
    return dfs(dp, 0, maxChoosableInteger, desiredTotal);
  }

  private boolean dfs(int[] dp, int state, int max, int target) {
    if (target <= 0) {
      return false;
    }
    if (dp[state] != 0) {
      return dp[state] == 1;
    }

    for (int i = 0; i < max; i++) {
      // next state not used
      if ((state & (1 << i)) == 0) {
        // return true immediately if false
        if (!dfs(dp, state | (1 << i), max, target - (i + 1))) {
          dp[state] = 1;
          return true;
        }
      }
    }
    dp[state] = -1;
    return false;
  }
}
