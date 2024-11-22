
// LC-1022
// https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/

public class SumOfRootToLeafBinaryNumbers {

  public int sumRootToLeaf(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return dfs(root, 0);
  }

  private int dfs(TreeNode n, int x) {
    if (n == null) {
      return 0;
    }

    x = (x << 1) | n.val;
    if (n.left == null && n.right == null) {
      return x;
    }

    return dfs(n.left, x) + dfs(n.right, x);
  }
}
