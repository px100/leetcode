import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// LC-1110
// https://leetcode.com/problems/delete-nodes-and-return-forest/

public class DeleteNodesAndReturnForest {

  public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    Set<Integer> set = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());

    List<TreeNode> result = new ArrayList<>();
    if (!set.contains(root.val)) {
      result.add(root);
    }
    dfs(result, root, set);

    return result;
  }

  private boolean dfs(List<TreeNode> result, TreeNode root, Set<Integer> to_delete) {
    if (root == null) {
      return true;
    }

    boolean delete_left = dfs(result, root.left, to_delete);
    boolean delete_right = dfs(result, root.right, to_delete);

    if (to_delete.contains(root.val)) {
      if (!delete_left) {
        result.add(root.left);
      }
      if (!delete_right) {
        result.add(root.right);
      }
      return true;
    } else {
      if (delete_left) {
        root.left = null;
      }
      if (delete_right) {
        root.right = null;
      }
      return false;
    }
  }
}
