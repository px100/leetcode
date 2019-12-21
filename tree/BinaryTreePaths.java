import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-paths/

public class BinaryTreePaths {

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> results = new LinkedList<>();
    if (root == null) {
      return results;
    }

    helper(root, results, "");

    return results;
  }

  private void helper(TreeNode root, List<String> results, String path) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      results.add(path + root.val);
      return;
    }

    path += root.val + "->";
    helper(root.left, results, path);
    helper(root.right, results, path);
  }
}
