
// LC-450
// https://leetcode.com/problems/delete-node-in-a-bst/

public class DeleteNodeInABST {

  public TreeNode deleteNode(TreeNode root, int key) {
    return helper(root, key);
  }

  private TreeNode helper(TreeNode root, int val) {
    if (root == null) {
      return null;
    }

    if (val > root.val) {
      root.right = helper(root.right, val);
    } else if (val < root.val) {
      root.left = helper(root.left, val);
    } else {
      if (root.right == null) {
        return root.left;
      } else if (root.left == null) {
        return root.right;
      } else {
        root.val = minVal(root.right);
        root.right = helper(root.right, root.val);
      }
    }

    return root;
  }

  private int minVal(TreeNode node) {
    int min = node.val;
    while (node.left != null) {
      min = node.left.val;
      node = node.left;
    }

    return min;
  }
}
