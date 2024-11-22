
// LC-979
// https://leetcode.com/problems/distribute-coins-in-binary-tree/

public class DistributeCoinsInBinaryTree {

  private int result = 0;

  public int distributeCoins(TreeNode root) {
    postorder(root);

    return result;
  }

  public int postorder(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int left = postorder(node.left);
    int right = postorder(node.right);

    // the sign of the diff indicates the direction of the flow of the coins
    int diff = node.val + left + right - 1;
    result += Math.abs(diff);

    return diff;
  }
}
