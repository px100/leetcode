
// LC-563
// https://leetcode.com/problems/binary-tree-tilt/

public class BinaryTreeTilt {

  private int tilt = 0;

  public int findTilt(TreeNode root) {
    helper(root);

    return tilt;
  }

  public int helper(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = helper(root.left);
    int right = helper(root.right);

    tilt += Math.abs(left - right);

    return left + right + root.val;
  }
}
