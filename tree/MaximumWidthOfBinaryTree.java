import java.util.LinkedList;
import java.util.Queue;

// LC-662
// https://leetcode.com/problems/maximum-width-of-binary-tree/

public class MaximumWidthOfBinaryTree {

  private static class TreeNodePair {

    public int position;
    public TreeNode node;

    public TreeNodePair(TreeNode x, int z) {
      this.node = x;
      this.position = z;
    }
  }

  public int widthOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }

    Queue<TreeNodePair> queue = new LinkedList<>();
    queue.offer(new TreeNodePair(root, 0));
    int ret = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      int left = Integer.MAX_VALUE, right = 0;
      if (size == 1) {
        queue.peek().position = 0;
      }
      for (int i = 0; i < size; i++) {
        TreeNodePair node = queue.poll();
        int position = node.position;
        // most left node
        if (i == 0) {
          left = Math.min(position, left);
        }
        // most right node
        if (i == size - 1) {
          right = Math.max(right, position);
        }
        // we add the left and right son of this node into queue
        TreeNode _node = node.node;
        if (_node.left != null) {
          queue.offer(new TreeNodePair(_node.left, position * 2));
        }
        if (_node.right != null) {
          queue.offer(new TreeNodePair(_node.right, position * 2 + 1));
        }
      }
      ret = Math.max(ret, right - left + 1);
    }

    return ret;
  }
}
