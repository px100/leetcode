
// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

public class FlattenBinaryTreeToLinkedList {

  private TreeNode prev = null;

  public void flatten(TreeNode root) {
    if (root == null) {
      return;
    }

    TreeNode left = root.left;
    TreeNode right = root.right;
    // handle the case of starting from the root of the tree where there is no previous node
    if (prev != null) {
      prev.right = root;
    }
    prev = root;
    prev.left = null;
    // update previous node and start preorder traversal
    flatten(left);
    flatten(right);
  }
}
