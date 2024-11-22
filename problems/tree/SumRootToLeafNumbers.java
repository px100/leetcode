
// https://leetcode.com/problems/sum-root-to-leaf-numbers/

public class SumRootToLeafNumbers {

  public int sumNumbers(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return helper(root, 0);
  }

  private int helper(TreeNode root, int numSoFar) {
    if (root == null) {
      return 0;
    }

    int currentNum = numSoFar * 10 + root.val;
    if (root.left == null && root.right == null) {
      return currentNum;
    }

    return helper(root.left, currentNum) + helper(root.right, currentNum);
  }
}
