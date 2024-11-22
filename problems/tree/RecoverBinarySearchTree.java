
// https://leetcode.com/problems/recover-binary-search-tree/

public class RecoverBinarySearchTree {

  private TreeNode pre = null;
  private TreeNode firstTarget = null;
  private TreeNode secondTarget = null;

  public void recoverTree(TreeNode root) {
    if (root == null) {
      return;
    }

    dfs(root);

    int temp = firstTarget.val;
    firstTarget.val = secondTarget.val;
    secondTarget.val = temp;
  }

  private void dfs(TreeNode root) {
    if (root == null) {
      return;
    }

    dfs(root.left);

    if (pre != null && root.val < pre.val) {
      if (firstTarget == null) {
        firstTarget = pre;
      }
      secondTarget = root;
    }
    pre = root;
    dfs(root.right);
  }
}
