import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/binary-search-tree-iterator/

public class BinarySearchTreeIterator {

  private class BSTIterator {

    Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
      TreeNode cur = root;
      while (cur != null) {
        stack.addFirst(cur);
        cur = cur.left;
      }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
      TreeNode node = stack.removeFirst();
      TreeNode cur = node.right;
      while (cur != null) {
        stack.addFirst(cur);
        cur = cur.left;
      }

      return node.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
      return !stack.isEmpty();
    }
  }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
