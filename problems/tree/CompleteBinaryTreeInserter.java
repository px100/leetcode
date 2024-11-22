import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// LC-919
// https://leetcode.com/problems/complete-binary-tree-inserter/

public class CompleteBinaryTreeInserter {

  private static class CBTInserter {

    TreeNode root;
    Deque<TreeNode> deque;

    public CBTInserter(TreeNode root) {
      this.root = root;
      deque = new LinkedList<>();
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);

      while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        if (node.left == null || node.right == null) {
          deque.offerLast(node);
        }
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
    }

    public int insert(int v) {
      TreeNode node = deque.peekFirst();
      deque.offerLast(new TreeNode(v));
      if (node.left == null) {
        node.left = deque.peekLast();
      } else {
        node.right = deque.peekLast();
        deque.pollFirst();
      }

      return node.val;
    }

    public TreeNode get_root() {
      return root;
    }
  }
}
