import java.util.LinkedList;
import java.util.Queue;

// LC-958
// https://leetcode.com/problems/check-completeness-of-a-binary-tree/

public class CheckCompletenessOfABinaryTree {

  public boolean isCompleteTree(TreeNode root) {
    if (root == null) {
      return true;
    }

    boolean seen = false;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      if (cur == null) {
        if (!seen) {
          seen = true;
        }
        continue;
      } else if (seen) {
        return false;
      }
      queue.offer(cur.left);
      queue.offer(cur.right);
    }

    return true;
  }
}
