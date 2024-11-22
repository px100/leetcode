import java.util.HashMap;
import java.util.Map;

// LC-437
// https://leetcode.com/problems/path-sum-iii/

public class PathSumIII {

  Map<Integer, Integer> map = new HashMap<>();
  int sums = 0;

  // Map the current sum at each node to the number of times that sum occurs.
  // Then at each node, calculate the difference between the current sum and the target sum.
  // If there exists such a key in the map, increment the counter by the value of the key.

  // current sum = sum of the node plus all of its ancestors

  public int pathSum(TreeNode root, int sum) {
    if (root == null) {
      return 0;
    }

    map.put(0, 1);  // 0 indicates that the current sum == the target
    getSum(root, 0, sum);
    return sums;
  }

  private void getSum(TreeNode node, int currSum, int sum) {
    if (node == null) {
      return;
    }

    currSum += node.val;
    int diff = currSum - sum;

    // Sse if the difference is a previous current sum
    if (map.containsKey(diff)) {
      sums += map.get(diff);
    }

    // If the current sum already exists, increment its value,
    // otherwise add to map with a value of 1
    int k = map.getOrDefault(currSum, 0);
    map.put(currSum, k + 1);

    // Recurse right and left subtrees
    getSum(node.left, currSum, sum);
    getSum(node.right, currSum, sum);

    // Backtrack
    map.put(currSum, map.get(currSum) - 1);
  }
}
