import java.util.Stack;

// https://leetcode.com/problems/validate-binary-search-tree/

public class ValidateBinarySearchTree {

  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }

    return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean helper(TreeNode root, long min, long max) {
    if (root == null) {
      return true;
    }

    if (root.val < min || root.val > max) {
      return false;
    }

    return helper(root.left, min, root.val - 1L)
      && helper(root.right, root.val + 1L, max);
  }

  public boolean isValidBSTInorder(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    double inorder = Double.MIN_VALUE;

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      // If next element in inorder traversal
      // is smaller than the previous one
      // that's not BST.
      if (root.val <= inorder) {
        return false;
      }
      inorder = root.val;
      root = root.right;
    }

    return true;
  }
}
