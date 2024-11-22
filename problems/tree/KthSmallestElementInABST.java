
// https://leetcode.com/problems/kth-smallest-element-in-a-bst/

public class KthSmallestElementInABST {

  int ans = 0;
  int temp = 0;

  public int kthSmallest(TreeNode root, int k) {
    helper(root, k);

    return ans;
  }

  private void helper(TreeNode root, int k) {
    if (root.left != null) {
      helper(root.left, k);
    }
    temp++;
    if (temp == k) {
      ans = root.val;
      return;
    }
    if (root.right != null) {
      helper(root.right, k);
    }
  }
}
