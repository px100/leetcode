
// https://leetcode.com/problems/house-robber-iii/

public class HouseRobberIII {

  public int rob(TreeNode root) {
    int[] rob = helper(root);
    return Math.max(rob[0], rob[1]);
  }

  private int[] helper(TreeNode root) {
    if (root == null) {
      return new int[2];
    }

    int[] left = helper(root.left);
    int[] right = helper(root.right);

    int[] result = new int[2];
    // root rob. next not rob
    result[0] = root.val + (left[1] + right[1]);
    // root not rob, next rob
    result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    return result;
  }
}
