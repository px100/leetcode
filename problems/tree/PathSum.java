import java.util.Stack;

// https://leetcode.com/problems/path-sum/

public class PathSum {

  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }

    sum -= root.val;
    if (root.left == null && root.right == null) {
      return sum == 0;
    }

    return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
  }

  public boolean hasPathSumIterative(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }

    Stack<TreeNode> stack = new Stack<>();
    Stack<Integer> vals = new Stack<>();
    stack.push(root);
    vals.push(sum);

    while (!stack.isEmpty()) {
      int val = vals.pop();
      TreeNode top = stack.pop();

      if (top.left == null && top.right == null && top.val == val) {
        return true;
      }

      if (top.left != null) {
        stack.push(top.left);
        vals.push(val - top.val);
      }

      if (top.right != null) {
        stack.push(top.right);
        vals.push(val - top.val);
      }
    }

    return false;
  }
}
