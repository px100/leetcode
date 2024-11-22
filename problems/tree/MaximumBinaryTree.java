
// LC-654
// https://leetcode.com/problems/maximum-binary-tree/

public class MaximumBinaryTree {

  public TreeNode constructMaximumBinaryTree(int[] nums) {
    return helper(nums, 0, nums.length - 1);
  }

  private TreeNode helper(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    }

    int max = nums[start];
    int index = start;
    for (int i = start; i <= end; i++) {
      if (nums[i] > max) {
        max = nums[i];
        index = i;
      }
    }

    TreeNode node = new TreeNode(max);
    node.left = helper(nums, start, index - 1);
    node.right = helper(nums, index + 1, end);

    return node;
  }
}
