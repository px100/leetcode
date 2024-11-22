import java.util.LinkedList;
import java.util.Queue;

// LC-513
// https://leetcode.com/problems/find-bottom-left-tree-value/

public class FindBottomLeftTreeValue {

  public int findBottomLeftValue(TreeNode root) {
    int leftValue = root.val;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if (i == 0) {
          leftValue = node.val;
        }
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
    }

    return leftValue;
  }
}
