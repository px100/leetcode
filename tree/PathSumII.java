import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/path-sum-ii/

public class PathSumII {

  private List<List<Integer>> res = new LinkedList<>();

  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    dfs(root, sum, new LinkedList<>());
    return res;
  }

  private void dfs(TreeNode root, int sum, List<Integer> path) {
    if (root == null) {
      return;
    }

    path.add(root.val);
    if (root.left == null && root.right == null && sum == root.val) {
      res.add(new ArrayList<>(path));
      path.remove(path.size() - 1);
      return;
    }

    dfs(root.left, sum - root.val, path);
    dfs(root.right, sum - root.val, path);
    path.remove(path.size() - 1);
  }
}
