
// LC-549
// https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/

public class BinaryTreeLongestConsecutiveSequenceII {

  private static class Result {

    int maxUp;
    int maxDown;
    int maxLength;

    public Result(int maxLength, int maxUp, int maxDown) {
      this.maxUp = maxUp;
      this.maxDown = maxDown;
      this.maxLength = maxLength;
    }
  }

  public int longestConsecutive(TreeNode root) {
    Result result = helper(root);

    return result.maxLength;
  }

  public Result helper(TreeNode root) {
    if (root == null) {
      return new Result(0, 0, 0);
    }

    Result left = helper(root.left);
    Result right = helper(root.right);

    int up = 0;
    int down = 0;

    if (root.left != null && root.left.val + 1 == root.val) {
      down = Math.max(down, left.maxDown + 1);
    }

    if (root.left != null && root.left.val - 1 == root.val) {
      up = Math.max(up, left.maxUp + 1);
    }

    if (root.right != null && root.right.val + 1 == root.val) {
      down = Math.max(down, right.maxDown + 1);
    }

    if (root.right != null && root.right.val - 1 == root.val) {
      up = Math.max(up, right.maxUp + 1);
    }

    int max = Math.max(Math.max(left.maxLength, right.maxLength), up + down + 1);

    return new Result(max, up, down);
  }
}
