import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-leaves-of-binary-tree/

public class FindLeavesOfBinaryTree {

  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(result, root);

    return result;
  }

  private int dfs(List<List<Integer>> list, TreeNode root) {
    if (root == null) {
      return -1;
    }

    int left = dfs(list, root.left);
    int right = dfs(list, root.right);
    int curr = Math.max(left, right) + 1;

    // first time this condition is reached is when curr = 0,
    // since the tree is bottom-up processed.
    if (list.size() <= curr) {
      list.add(new ArrayList<>());
    }

    list.get(curr).add(root.val);

    return curr;
  }
}
