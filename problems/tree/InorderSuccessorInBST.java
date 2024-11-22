
// https://leetcode.com/problems/inorder-successor-in-bst/

public class InorderSuccessorInBST {

  public TreeNode inorderSuccessorA(TreeNode root, TreeNode p) {
    TreeNode successor = null;
    while (root != null) {
      if (p.val < root.val) {
        successor = root;
        root = root.left;
      } else {
        root = root.right;
      }
    }

    return successor;
  }

  // O(log(n)) - Balanced BST
  // O(n) - Worst case
  public TreeNode inorderSuccessorB(TreeNode root, TreeNode p) {
    if (root == null) {
      return null;
    }

    TreeNode next = null;
    TreeNode c = root;
    while (c != null && c.val != p.val) {
      if (c.val > p.val) {
        next = c;
        c = c.left;
      } else {
        c = c.right;
      }
    }

    if (c == null) {
      return null;
    }

    if (c.right == null) {
      return next;
    }

    c = c.right;
    while (c.left != null) {
      c = c.left;
    }

    return c;
  }
}
