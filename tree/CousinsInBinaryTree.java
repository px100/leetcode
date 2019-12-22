import java.util.LinkedList;
import java.util.Queue;

// LC-993
// https://leetcode.com/problems/cousins-in-binary-tree/

public class CousinsInBinaryTree {

  public boolean isCousins(TreeNode root, int x, int y) {

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int parent1 = -1;
      int parent2 = -1;

      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode cur = queue.poll();
        if ((cur.left != null && cur.left.val == x) || (cur.right != null && cur.right.val == x)) {
          parent1 = cur.val;
        }
        if ((cur.left != null && cur.left.val == y) || (cur.right != null && cur.right.val == y)) {
          parent2 = cur.val;
        }
        if (cur.left != null) {
          queue.add(cur.left);
        }
        if (cur.right != null) {
          queue.add(cur.right);
        }
      }
      if (parent2 != -1 && parent1 != -1 && parent1 != parent2) {
        return true;
      }
    }

    return false;
  }
}
