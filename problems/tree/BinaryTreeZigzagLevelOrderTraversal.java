import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

public class BinaryTreeZigzagLevelOrderTraversal {

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    int lev = -1;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      LinkedList<Integer> level = new LinkedList<>();
      int size = queue.size();
      lev++;
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.remove();
        if (lev % 2 == 0) {
          level.addLast(node.val);
        } else {
          level.addFirst(node.val);
        }

        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      res.add(level);
    }

    return res;
  }
}
