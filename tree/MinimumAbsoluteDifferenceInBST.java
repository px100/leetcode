
// LC-530
// https://leetcode.com/problems/minimum-absolute-difference-in-bst/

public class MinimumAbsoluteDifferenceInBST {

  private int prev = Integer.MAX_VALUE;
  private int min = Integer.MAX_VALUE;

  public int getMinimumDifference(TreeNode root) {
    getMinDifference(root);

    return min;
  }

  private void getMinDifference(TreeNode root) {
    if (root == null) {
      return;
    }

    getMinDifference(root.left);
    min = Math.min(min, Math.abs(root.val - prev));
    prev = root.val;
    getMinDifference(root.right);
  }
}
