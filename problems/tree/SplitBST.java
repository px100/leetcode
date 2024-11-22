
// LC-776
// https://leetcode.com/problems/split-bst/

public class SplitBST {

  public TreeNode[] splitBST(TreeNode root, int V) {
    TreeNode[] result = new TreeNode[]{null, null};
    if (root == null) {
      return result;
    }

    if (root.val <= V) {
      result = splitBST(root.right, V);
      root.right = result[0];
      result[0] = root;
    } else {
      result = splitBST(root.left, V);
      root.left = result[1];
      result[1] = root;
    }

    return result;
  }
}
