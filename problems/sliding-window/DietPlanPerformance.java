
// LC-1176
// https://leetcode.com/problems/diet-plan-performance/

public class DietPlanPerformance {

  public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
    if (calories == null || calories.length == 0) {
      return 0;
    }

    int sum = 0;
    int result = 0;

    for (int i = 0; i < calories.length; i++) {
      sum += calories[i];
      if (i < k - 1) {
        continue;
      }
      if (i >= k) {
        sum -= calories[i - k];
      }
      if (sum > upper) {
        result++;
      }
      if (sum < lower) {
        result--;
      }
    }

    return result;
  }
}
