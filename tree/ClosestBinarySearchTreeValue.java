
// https://leetcode.com/problems/closest-binary-search-tree-value/

public class ClosestBinarySearchTreeValue {

  private double gap = Double.MAX_VALUE;
  private int result = 0;

  public int closestValue(TreeNode root, double target) {
    if (root == null) {
      return Integer.MAX_VALUE;
    }

    closestValueHelper(root, target);

    return result;
  }

  private void closestValueHelper(TreeNode root, double target) {
    if (root == null) {
      return;
    }

    if (Math.abs(root.val - target) < gap) {
      gap = Math.abs(root.val - target);
      result = root.val;
    }

    if (target < root.val) {
      closestValueHelper(root.left, target);
    } else {
      closestValueHelper(root.right, target);
    }
  }

  public int closestValueIterative(TreeNode root, double target) {
    if (root == null) {
      return Integer.MAX_VALUE;
    }

    int result = 0;
    double gap = Double.MAX_VALUE;

    while (root != null) {
      if (root.val == target) {
        return root.val;
      }

      double dist = Math.abs(root.val - target);
      if (dist < gap) {
        result = root.val;
        gap = dist;
      }

      if (target > root.val) {
        root = root.right;
      } else if (target < root.val) {
        root = root.left;
      }
    }

    return result;
  }
}
