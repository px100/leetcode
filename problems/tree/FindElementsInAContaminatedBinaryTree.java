import java.util.BitSet;

// LC-1261
// https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/

public class FindElementsInAContaminatedBinaryTree {

  private static class FindElements {

    private BitSet sets = new BitSet();

    public FindElements(TreeNode root) {
      dfs(root, 0);
    }

    public boolean find(int target) {
      return sets.get(target);
    }

    private void dfs(TreeNode root, int val) {
      if (root == null) {
        return;
      }

      sets.set(val);
      dfs(root.left, 2 * val + 1);
      dfs(root.right, 2 * val + 2);
    }
  }
}
