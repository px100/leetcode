
// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

public class ConvertSortedArrayToBinarySearchTree {

  public TreeNode sortedArrayToBST(int[] nums) {
    return helper(nums, 0, nums.length - 1);
  }

  private TreeNode helper(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    }

    int mid = (start + end) >>> 1;

    TreeNode node = new TreeNode(nums[mid]);
    node.left = helper(nums, start, mid - 1);
    node.right = helper(nums, mid + 1, end);

    return node;
  }
}
