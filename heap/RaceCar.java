import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// LC-818
// https://leetcode.com/problems/race-car/
// https://leetcode.com/problems/race-car/solution/
// https://leetcode.com/problems/race-car/discuss/124326/Summary-of-the-BFS-and-DP-solutions-with-intuitive-explanation

public class RaceCar {

  // bfs
  public int racecar(int target) {
    Deque<int[]> queue = new LinkedList<>();
    queue.offerLast(new int[]{0, 1}); // starts from position 0 with speed 1

    Set<String> visited = new HashSet<>();
    visited.add(0 + " " + 1);

    for (int level = 0; !queue.isEmpty(); level++) {
      for (int k = queue.size(); k > 0; k--) {
        int[] cur = queue.pollFirst();  // cur[0] is position; cur[1] is speed

        if (cur[0] == target) {
          return level;
        }

        int[] next = new int[]{cur[0] + cur[1], cur[1] << 1};  // accelerate instruction
        String key = (next[0] + " " + next[1]);

        if (!visited.contains(key) && 0 < next[0] && next[0] < (target << 1)) {
          queue.offerLast(next);
          visited.add(key);
        }

        next = new int[]{cur[0], cur[1] > 0 ? -1 : 1};  // reverse instruction
        key = (next[0] + " " + next[1]);

        if (!visited.contains(key) && 0 < next[0] && next[0] < (target << 1)) {
          queue.offerLast(next);
          visited.add(key);
        }
      }
    }

    return -1;
  }

  public int racecarDP1(int target) {
    int[] dp = new int[target + 1];
    for (int i = 1; i <= target; i++) {
      int n = 32 - Integer.numberOfLeadingZeros(i);
      if ((1 << n) - 1 == i) {
        dp[i] = n;
        continue;
      }
      // pass and come back
      dp[i] = n + 1 + dp[(1 << n) - 1 - i];

      // get closer, step back (could be 0), then go forward
      for (int j = -1; j < n - 1; j++) {
        dp[i] = Math.min(dp[i],
          n - 1 + 1 + (j + 1) + 1 + dp[i - ((1 << (n - 1)) - 1 - ((1 << (j + 1)) - 1))]);
      }
    }

    return dp[target];
  }

  public int racecarDP2(int target) {
    // dp[i] contains num of min steps to reach target position i
    int[] dp = new int[target + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    dp[1] = 1;

    for (int i = 2; i <= target; i++) {
      // 2^(rightK-1) <= i < 2^(rightK)
      int rightK = 1, rightPos = 1;
      while (i > rightPos) {
        rightK++;
        rightPos = (1 << rightK) - 1;
      }

      // found the optimal solution right away, when 2^k - 1 = target i
      if (rightPos == i) {
        dp[i] = rightK;
        continue;
      }

      // examine crossing the target i then coming back
      dp[i] = Math.min(dp[i], rightK + 1 + dp[rightPos - i]);

      int leftK = rightK - 1;
      int leftPos = rightPos / 2;
      // examine stopping before the target, then reverse/reset
      // j steps in the reverse direction
      for (int j = 0; j < leftK; j++) {
        int reversePos = leftPos - (1 << j) + 1;
        dp[i] = Math.min(dp[i], leftK + 1 + j + 1 + dp[i - reversePos]);
      }
    }

    return dp[target];
  }
}
