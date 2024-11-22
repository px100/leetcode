
// LC-938
// https://leetcode.com/problems/range-sum-of-bst/

public class RangeSumOfBST {

  private int sum = 0;

  public int rangeSumBST(TreeNode root, int L, int R) {
    sum = 0;
    dfs(root, L, R);

    return sum;
  }

  private void dfs(TreeNode node, int L, int R) {
    if (node == null) {
      return;
    }

    if (L <= node.val && node.val <= R) {
      sum += node.val;
    }
    if (L < node.val) {
      dfs(node.left, L, R);
    }
    if (node.val < R) {
      dfs(node.right, L, R);
    }
  }
}
