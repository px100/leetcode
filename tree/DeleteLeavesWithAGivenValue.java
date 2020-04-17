
// LC-1325
// https://leetcode.com/problems/delete-leaves-with-a-given-value/

public class DeleteLeavesWithAGivenValue {

  public TreeNode removeLeafNodes(TreeNode root, int target) {
    if (root == null) {
      return null;
    }
    root.left = removeLeafNodes(root.left, target);
    root.right = removeLeafNodes(root.right, target);
    return isLeaf(root) && root.val == target ? null : root;
  }

  public boolean isLeaf(TreeNode node) {
    return node.left == null && node.right == null;
  }
}
