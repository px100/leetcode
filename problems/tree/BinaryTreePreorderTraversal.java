import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/binary-tree-preorder-traversal/

public class BinaryTreePreorderTraversal {

  public List<Integer> preorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> res = new ArrayList<>();

    if (root == null) {
      return res;
    }

    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      res.add(node.val);

      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }

    return res;
  }

  public List<Integer> preorderTraversalRec(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    preOrder(root, res);

    return res;
  }

  private void preOrder(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }

    res.add(root.val);
    preOrder(root.left, res);
    preOrder(root.right, res);
  }
}
