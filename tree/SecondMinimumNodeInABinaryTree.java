import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// LC-671
// https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/

public class SecondMinimumNodeInABinaryTree {

  public int findSecondMinimumValue(TreeNode root) {
    Set<Integer> uniques = new HashSet<>();
    dfs(root, uniques);

    int min = root.val;
    long secondMin = Long.MAX_VALUE;

    for (int v : uniques) {
      if (min < v && v < secondMin) {
        secondMin = v;
      }
    }

    return secondMin < Long.MAX_VALUE ? (int) secondMin : -1;
  }

  private void dfs(TreeNode root, Set<Integer> uniques) {
    if (root == null) {
      return;
    }

    uniques.add(root.val);
    dfs(root.left, uniques);
    dfs(root.right, uniques);
  }

  public int findSecondMinimumValueBFS(TreeNode root) {
    if (root == null || root.right == null || root.left == null) {
      return -1;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    Integer secondMin = null;
    while (!queue.isEmpty()) {
      TreeNode curr = queue.poll();
      if (curr.right != null) {
        queue.offer(curr.right);
      }
      if (curr.left != null) {
        queue.offer(curr.left);
      }
      if (curr.val != root.val) {
        secondMin = secondMin == null ? curr.val : Math.min(secondMin, curr.val);
      }
    }

    return secondMin == null ? -1 : secondMin;
  }
}
