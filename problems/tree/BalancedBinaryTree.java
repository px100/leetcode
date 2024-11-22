
// https://leetcode.com/problems/balanced-binary-tree/

public class BalancedBinaryTree {

  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }

    return height(root) != -1;
  }

  private int height(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int lh = height(root.left);
    if (lh == -1) {
      return -1;
    }

    int rh = height(root.right);
    if (rh == -1) {
      return -1;
    }

    return Math.abs(lh - rh) > 1 ? -1 : Math.max(lh, rh) + 1;
  }
}
