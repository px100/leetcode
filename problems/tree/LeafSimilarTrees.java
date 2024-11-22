import java.util.ArrayList;
import java.util.List;

// LC-872
// https://leetcode.com/problems/leaf-similar-trees/

public class LeafSimilarTrees {

  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    List<Integer> leaves1 = new ArrayList<>();
    List<Integer> leaves2 = new ArrayList<>();

    dfs(root1, leaves1);
    dfs(root2, leaves2);

    return leaves1.equals(leaves2);
  }

  private void dfs(TreeNode node, List<Integer> values) {
    if (node == null) {
      return;
    }

    if (node.left == null && node.right == null) {
      values.add(node.val);
    }
    dfs(node.left, values);
    dfs(node.right, values);
  }
}
