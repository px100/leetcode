
// LC-889
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

  private int preIndex = 0;
  private int postIndex = 0;

  public TreeNode constructFromPrePost(int[] pre, int[] post) {
    TreeNode root = new TreeNode(pre[preIndex++]);
    if (root.val != post[postIndex]) {
      root.left = constructFromPrePost(pre, post);
    }

    if (root.val != post[postIndex]) {
      root.right = constructFromPrePost(pre, post);
    }
    postIndex++;

    return root;
  }
}
