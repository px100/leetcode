import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0) {
      return null;
    }

    TreeNode root = new TreeNode(preorder[0]);
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);

    // i is preorder index
    // j is inorder index
    for (int i = 1, j = 0; i < preorder.length; i++) {
      TreeNode cur = new TreeNode(preorder[i]);
      TreeNode parent = stack.peekFirst();
      if (stack.peekFirst().val != inorder[j]) {
        parent.left = cur;
      } else {
        while (!stack.isEmpty() && stack.peekFirst().val == inorder[j]) {
          parent = stack.pop();
          j++;
        }
        parent.right = cur;
      }
      stack.push(cur);
    }

    return root;
  }
}
