
// LC-701
// https://leetcode.com/problems/insert-into-a-binary-search-tree/

public class InsertIntoABinarySearchTree {

  public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) {
      return new TreeNode(val);
    }

    if (val > root.val && root.right == null) {
      root.right = new TreeNode(val);
    }
    if (val < root.val && root.left == null) {
      root.left = new TreeNode(val);
    }

    if (val > root.val) {
      insertIntoBST(root.right, val);
    }
    if (val < root.val) {
      insertIntoBST(root.left, val);
    }

    return root;
  }
}
