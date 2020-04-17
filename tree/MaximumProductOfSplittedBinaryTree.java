
// LC-1339
// https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/

public class MaximumProductOfSplittedBinaryTree {

  private final long MOD = 1000000007;
  private int sum = 0;
  private int minDiff = Integer.MAX_VALUE;

  public int maxProduct(TreeNode root) {
    dfs(root);
    checkMax(root);
    int b = minDiff + (sum - minDiff) / 2;
    int a = b - minDiff;
    return (int) (((a % MOD) * (b % MOD)) % MOD);
  }

  public void dfs(TreeNode root) {
    if (root == null) {
      return;
    }
    sum += root.val;
    dfs(root.left);
    dfs(root.right);
  }

  public int checkMax(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int l = checkMax(root.left);
    int r = checkMax(root.right);
    minDiff = Math.min(minDiff, Math.abs((l + r + root.val) - (sum - l - r - root.val)));
    return l + r + root.val;
  }
}
