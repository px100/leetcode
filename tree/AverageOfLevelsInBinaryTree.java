import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// LC-637
// https://leetcode.com/problems/average-of-levels-in-binary-tree/

public class AverageOfLevelsInBinaryTree {

  public List<Double> averageOfLevels(TreeNode root) {
    List<Double> avgs = new ArrayList<>();
    if (root == null) {
      return avgs;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      double sum = 0;
      double count = 0;
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode cur = queue.poll();
        sum += cur.val;
        count++;
        if (cur.left != null) {
          queue.offer(cur.left);
        }
        if (cur.right != null) {
          queue.offer(cur.right);
        }
      }
      avgs.add(sum / count);
    }

    return avgs;
  }
}
