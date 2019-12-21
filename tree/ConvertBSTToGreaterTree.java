
// LC-538
// https://leetcode.com/problems/convert-bst-to-greater-tree/

public class ConvertBSTToGreaterTree {

  private int sum = 0;

  public TreeNode convertBST(TreeNode root) {
    helper(root);

    return root;
  }

  public void helper(TreeNode root) {
    if (root == null) {
      return;
    }

    helper(root.right);
    root.val += sum;
    sum = root.val;
    helper(root.left);
  }
}
