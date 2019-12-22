import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// LC-666
// https://leetcode.com/problems/path-sum-iv/
// https://leetcode.com/articles/path-sum-iv/

public class PathSumIV {

  private int ans = 0;
  private Map<Integer, Integer> values = new HashMap<>();

  public int pathSum(int[] nums) {
    Arrays.stream(nums).forEach(num -> values.put(num / 10, num % 10));
    dfs(nums[0] / 10, 0);

    return ans;
  }

  private void dfs(int node, int sum) {
    if (!values.containsKey(node)) {
      return;
    }

    sum += values.get(node);

    int depth = node / 10, pos = node % 10;
    int left = (depth + 1) * 10 + 2 * pos - 1;
    int right = left + 1;

    if (values.containsKey(left) || values.containsKey(right)) {
      dfs(left, sum);
      dfs(right, sum);
    } else {
      ans += sum;
    }
  }
}
