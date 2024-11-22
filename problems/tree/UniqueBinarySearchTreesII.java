import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/unique-binary-search-trees-ii/

public class UniqueBinarySearchTreesII {

  private LinkedList<TreeNode> helper(int start, int end) {
    LinkedList<TreeNode> res = new LinkedList<>();
    if (start > end) {
      res.add(null);

      return res;
    }

    for (int i = start; i <= end; i++) {
      LinkedList<TreeNode> left = helper(start, i - 1);
      LinkedList<TreeNode> right = helper(i + 1, end);

      for (TreeNode l : left) {
        for (TreeNode r : right) {
          TreeNode cur = new TreeNode(i);
          cur.left = l;
          cur.right = r;
          res.add(cur);
        }
      }
    }

    return res;
  }

  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return Collections.emptyList();
    }

    return helper(1, n);
  }
}
