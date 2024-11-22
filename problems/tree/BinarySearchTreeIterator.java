
// LC-173
// https://leetcode.com/problems/binary-search-tree-iterator/

import java.util.Stack;

public class BinarySearchTreeIterator {

  private class BSTIterator {

    Stack<TreeNode> stack;

    private void leftmostInorder(TreeNode node) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
    }

    public BSTIterator(TreeNode root) {
      stack = new Stack<>();
      leftmostInorder(root);
    }

    public int next() {
      TreeNode topmostNode = stack.pop();
      if (topmostNode.right != null) {
        leftmostInorder(topmostNode.right);
      }
      return topmostNode.val;
    }

    public boolean hasNext() {
      return !stack.isEmpty();
    }
  }

/*
  Your BSTIterator object will be instantiated and called as such:
  BSTIterator obj = new BSTIterator(root);
  int param_1 = obj.next();
  boolean param_2 = obj.hasNext();
 */
}
