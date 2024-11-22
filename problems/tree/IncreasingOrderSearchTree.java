
// LC-897
// https://leetcode.com/problems/increasing-order-search-tree/

public class IncreasingOrderSearchTree {

  private TreeNode cur;

  public TreeNode increasingBST(TreeNode root) {
    TreeNode ans = new TreeNode(-1);
    cur = ans;
    inorder(root);

    return ans.right;
  }

  private void inorder(TreeNode node) {
    if (node == null) {
      return;
    }

    inorder(node.left);
    node.left = null;
    cur.right = node;
    cur = node;
    inorder(node.right);
  }
}
