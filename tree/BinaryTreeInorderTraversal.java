import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/binary-tree-inorder-traversal/

public class BinaryTreeInorderTraversal {

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while (cur != null || !stack.isEmpty()) {
      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }
      cur = stack.pop();
      res.add(cur.val);
      cur = cur.right;
    }

    return res;
  }

  public List<Integer> inorderTraversalMorris(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    TreeNode cur = root;

    while (cur != null) {
      if (cur.left != null) {
        TreeNode pre = cur.left;
        while ((pre.right != null) && (pre.right != cur)) {
          pre = pre.right;
        }
        if (pre.right == null) {
          pre.right = cur;
          cur = cur.left;
        } else {
          pre.right = null;
          res.add(cur.val);
          cur = cur.right;
        }
      } else {
        res.add(cur.val);
        cur = cur.right;
      }
    }

    return res;
  }
}
