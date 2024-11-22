
// LC-1008
// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

public class ConstructBinarySearchTreeFromPreorderTraversal {

  public TreeNode bstFromPreorder(int[] preorder) {
    if (preorder.length == 0) {
      return null;
    }

    TreeNode root = new TreeNode(preorder[0]);
    for (int i = 1; i < preorder.length; i++) {
      helper(preorder[i], root);
    }

    return root;
  }

  private void helper(int val, TreeNode root) {
    if (root == null) {
      return;
    }

    if (val < root.val) {
      if (root.left == null) {
        root.left = new TreeNode(val);
      } else {
        helper(val, root.left);
      }
    } else {
      if (root.right == null) {
        root.right = new TreeNode(val);
      } else {
        helper(val, root.right);
      }
    }
  }
}
