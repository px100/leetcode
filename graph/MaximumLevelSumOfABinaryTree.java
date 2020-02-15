import java.util.LinkedList;
import java.util.Queue;

// LC-1161
// https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/

public class MaximumLevelSumOfABinaryTree {

  public int maxLevelSum(TreeNode root) {
    int maxSum = Integer.MIN_VALUE;
    int minLevel = 1;
    int currLevel = 0;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (queue.size() > 0) {
      int size = queue.size();
      currLevel++;

      int levelSum = 0;
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        levelSum += node.val;
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }
      if (maxSum < levelSum) {
        minLevel = currLevel;
        maxSum = levelSum;
      }
    }

    return minLevel;
  }
}
