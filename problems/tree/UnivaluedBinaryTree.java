
// LC-965
// https://leetcode.com/problems/univalued-binary-tree/

// A binary tree is univalued if every node in the tree has the same value.
// Return true if and only if the given tree is univalued.

public class UnivaluedBinaryTree {

  public boolean isUnivalTree(TreeNode root) {
    if (root == null) {
      return true;
    }

    boolean l = root.left == null || ((root.val == root.left.val) && isUnivalTree(root.left));
    boolean r = root.right == null || ((root.val == root.right.val) && isUnivalTree(root.right));

    return l && r;
  }
}
