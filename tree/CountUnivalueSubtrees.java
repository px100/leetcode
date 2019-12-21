
// https://leetcode.com/problems/count-univalue-subtrees

// divide-and-conquer.
// Use different return values to mark different cases.
// Integer.MIN_VALUE => Mark the subtree is not univalued.
// Integer.MAX_VALUE => Mark if the root is null.

public class CountUnivalueSubtrees {

  private int count = 0;

  public int countUnivalSubtrees(TreeNode root) {
    if (root == null) {
      return 0;
    }

    countUnivalSubtreesHelper(root);

    return count;
  }

  private int countUnivalSubtreesHelper(TreeNode root) {
    if (root == null) {
      return Integer.MAX_VALUE;
    }

    int left = countUnivalSubtreesHelper(root.left);
    int right = countUnivalSubtreesHelper(root.right);

    // left and right are all empty
    if (left == Integer.MAX_VALUE && right == Integer.MAX_VALUE) {
      count++;
      return root.val;
    } else if (left == Integer.MAX_VALUE || right == Integer.MAX_VALUE) {
      if (root.val == left || root.val == right) {
        count++;
        return root.val;
      } else {
        return Integer.MIN_VALUE;
      }
    } else {
      if (root.val == left && root.val == right) {
        count++;
        return root.val;
      } else {
        return Integer.MIN_VALUE;
      }
    }
  }
}
