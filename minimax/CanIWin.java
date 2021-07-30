
// LC-464
// https://leetcode.com/problems/can-i-win/

import java.util.HashMap;
import java.util.Map;

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

  public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
    if (desiredTotal <= 0) {
      return true;
    }
    int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
    if (sum < desiredTotal) {
      return false;
    }
    return dfs2(0, desiredTotal, maxChoosableInteger, new HashMap<>());
  }

  private boolean dfs2(int state, int max, int target, Map<Integer, Boolean> dp) {
    if (target <= 0) {
      return false;
    }
    if (dp.containsKey(state)) {
      return dp.get(state);
    }

    for (int i = 0; i < max; i++) {
      // next state not used
      if ((state & (1 << i)) == 0) {
        // return true immediately if false
        if (!dfs2(state | (1 << i), max, target - (i + 1), dp)) {
          return dp.computeIfAbsent(state, k -> true);
        }
      }
    }
    return dp.computeIfAbsent(state, k -> false);
  }
}
