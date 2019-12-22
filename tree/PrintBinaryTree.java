import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// LC-655
// https://leetcode.com/problems/print-binary-tree/

public class PrintBinaryTree {

  private int height(TreeNode cur) {
    if (cur == null) {
      return 0;
    }

    return 1 + Math.max(height(cur.left), height(cur.right));
  }

  public List<List<String>> printTree(TreeNode root) {
    int rows = height(root);
    int columns = (1 << rows) - 1;

    List<List<String>> result = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      result.add(new ArrayList<>());
      for (int j = 0; j < columns; j++) {
        result.get(i).add("");
      }
    }

    Queue<TreeNode> queue = new LinkedList<>();
    Queue<int[]> indexQueue = new LinkedList<>();
    queue.offer(root);
    indexQueue.offer(new int[]{0, columns - 1, 0});

    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();

      int[] i = indexQueue.poll();
      int l = i[0];
      int r = i[1];
      int row = i[2];

      int mid = l + (r - l) / 2;

      result.get(row).set(mid, "" + cur.val);

      if (cur.left != null) {
        queue.offer(cur.left);
        indexQueue.offer(new int[]{l, mid - 1, row + 1});
      }

      if (cur.right != null) {
        queue.offer(cur.right);
        indexQueue.offer(new int[]{mid + 1, r, row + 1});
      }
    }

    return result;
  }
}
