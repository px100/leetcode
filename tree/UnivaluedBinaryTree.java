
// LC-965
// https://leetcode.com/problems/univalued-binary-tree/

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
