import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC-652
// https://leetcode.com/problems/find-duplicate-subtrees/

public class FindDuplicateSubtrees {

  private int t = 1;
  private List<TreeNode> result = new ArrayList<>();
  private Map<String, Integer> trees = new HashMap<>();
  private Map<Integer, Integer> count = new HashMap<>();

  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    dfs(root);

    return result;
  }

  private int dfs(TreeNode node) {
    if (node == null) {
      return 0;
    }

    String serial = node.val + "," + dfs(node.left) + "," + dfs(node.right);

    int identifier = trees.computeIfAbsent(serial, x -> t++);
    count.merge(identifier, 1, Integer::sum);
    if (count.get(identifier) == 2) {
      result.add(node);
    }

    return identifier;
  }
}
