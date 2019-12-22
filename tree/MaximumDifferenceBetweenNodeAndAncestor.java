
// LC-1026
// https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/

public class MaximumDifferenceBetweenNodeAndAncestor {

  public int maxAncestorDiff(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return maxDiffRec(root, root.val, root.val);
  }

  private int maxDiffRec(TreeNode node, int min, int max) {
    min = Math.min(node.val, min);
    max = Math.max(node.val, max);

    int diff = Math.max(Math.abs(node.val - max), Math.abs(node.val - min));
    if (node.left != null) {
      diff = Math.max(diff, maxDiffRec(node.left, min, max));
    }
    if (node.right != null) {
      diff = Math.max(diff, maxDiffRec(node.right, min, max));
    }

    return diff;
  }
}
