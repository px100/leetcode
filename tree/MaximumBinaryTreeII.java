
// LC-998
// https://leetcode.com/problems/maximum-binary-tree-ii/

public class MaximumBinaryTreeII {

  public TreeNode insertIntoMaxTree(TreeNode root, int val) {
    if (root == null) {
      return new TreeNode(val);
    }

    if (val < root.val) {
      root.right = insertIntoMaxTree(root.right, val);
    } else {
      TreeNode t = new TreeNode(val);
      t.left = root;
      root = t;
    }

    return root;
  }
}
