
// LC-865
// https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/

public class SmallestSubtreeWithAllTheDeepestNodes {

  private TreeNode res = null;
  private int max = Integer.MIN_VALUE;

  public TreeNode subtreeWithAllDeepest(TreeNode root) {
    res = root;
    dfs(root, 1);

    return res;
  }

  private int dfs(TreeNode root, int level) {
    if (root == null) {
      return level - 1;
    }

    int left = dfs(root.left, level + 1);
    int right = dfs(root.right, level + 1);
    int cur = Math.max(left, right);
    max = Math.max(max, cur);
    if (left == max && right == max) {
      res = root;
    }

    return cur;
  }
}
