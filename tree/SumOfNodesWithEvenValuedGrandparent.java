
// LC-1315
// https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/

public class SumOfNodesWithEvenValuedGrandparent {

  private int sum = 0;

  public int sumEvenGrandparent(TreeNode root) {
    dfs(root, false, false);
    return sum;
  }

  private void dfs(TreeNode node, boolean grandParent, boolean parent) {
    if (node == null) {
      return;
    }
    if (grandParent) {
      sum += node.val;
    }
    dfs(node.left, parent, node.val % 2 == 0);
    dfs(node.right, parent, node.val % 2 == 0);
  }
}
