
// https://leetcode.com/problems/invert-binary-tree/

public class InvertBinaryTree {

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return root;
    }

    TreeNode right = invertTree(root.right);
    TreeNode left = invertTree(root.left);

    root.left = right;
    root.right = left;

    return root;
  }
}
