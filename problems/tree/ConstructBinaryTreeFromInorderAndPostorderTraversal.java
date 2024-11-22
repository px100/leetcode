import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

  private int postorderIndex;
  private Map<Integer, Integer> indexMap = new HashMap<>();

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    postorderIndex = postorder.length - 1;

    int idx = 0;
    for (int val : inorder) {
      indexMap.put(val, idx++);
    }

    return constructTree(0, inorder.length - 1, inorder, postorder);
  }

  private TreeNode constructTree(int left, int right, int[] inorder, int[] postorder) {
    if (left > right) {
      return null;
    }

    int rootVal = postorder[postorderIndex--];
    int inorderIndex = indexMap.get(rootVal);

    TreeNode root = new TreeNode(rootVal);
    root.right = constructTree(inorderIndex + 1, right, inorder, postorder);
    root.left = constructTree(left, inorderIndex - 1, inorder, postorder);

    return root;
  }
}
