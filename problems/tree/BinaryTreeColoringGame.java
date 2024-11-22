
// LC-1145
// https://leetcode.com/problems/binary-tree-coloring-game/

public class BinaryTreeColoringGame {

  public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
    TreeNode cur = findNode(root, x);
    if (findSize(cur.left) > n / 2) {
      return true;
    }
    if (findSize(cur.right) > n / 2) {
      return true;
    }

    return findSize(cur) <= n / 2;
  }

  private int findSize(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return findSize(root.left) + findSize(root.right) + 1;
  }

  private TreeNode findNode(TreeNode root, int x) {
    if (root == null) {
      return null;
    }
    if (root.val == x) {
      return root;
    }

    TreeNode left = findNode(root.left, x);
    if (left != null) {
      return left;
    }

    return findNode(root.right, x);
  }
}
