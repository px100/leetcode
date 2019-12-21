
// https://leetcode.com/problems/sum-of-left-leaves/

public class SumOfLeftLeaves {

  private int sum = 0;

  public int sumOfLeftLeaves(TreeNode root) {
    postorderDFS(root);

    return sum;
  }

  public void postorderDFS(TreeNode root) {
    if (root == null) {
      return;
    }

    postorderDFS(root.left);
    postorderDFS(root.right);

    if (root.left != null && root.left.left == null && root.left.right == null) {
      sum += root.left.val;
    }
  }
}
