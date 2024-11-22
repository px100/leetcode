
// LC-1120
// https://leetcode.com/problems/maximum-average-subtree/

public class MaximumAverageSubtree {

  private static class ResultType {

    int size;
    int sum;
    TreeNode node;

    public ResultType(TreeNode node, int size, int sum) {
      this.node = node;
      this.size = size;
      this.sum = sum;
    }
  }

  private ResultType result = null;

  public TreeNode findSubtree2(TreeNode root) {
    if (root == null) {
      return null;
    }

    helper(root);

    return result.node;
  }

  private ResultType helper(TreeNode node) {
    if (node == null) {
      return new ResultType(null, 0, 0);
    }

    ResultType left = helper(node.left);
    ResultType right = helper(node.right);
    ResultType cur =
      new ResultType(node, left.size + right.size + 1, left.sum + right.sum + node.val);

    if (result == null || cur.sum * result.size > result.sum * cur.size) {
      result = cur;
    }

    return cur;
  }
}
