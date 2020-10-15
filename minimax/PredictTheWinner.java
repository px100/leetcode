
// LC-486
// https://leetcode.com/problems/predict-the-winner/

public class PredictTheWinner {

  public boolean PredictTheWinner(int[] nums) {
    if (nums == null || nums.length % 2 == 0) {
      return true;
    }

    int n = nums.length;
    int[] dp = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        if (i == j) {
          dp[i] = nums[i];
        } else {
          dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
        }
      }
    }

    return dp[n - 1] >= 0;
  }
}
