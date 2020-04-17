import java.util.LinkedList;
import java.util.Queue;

// LC-1302
// https://leetcode.com/problems/deepest-leaves-sum/

public class DeepestLeavesSum {

  public int deepestLeavesSum(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int sum = 0;
    while (!queue.isEmpty()) {
      sum = 0;
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        sum += node.val;
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }
    }

    return sum;
  }
}
