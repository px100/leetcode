import java.util.ArrayList;
import java.util.List;

// LC-545
// https://leetcode.com/problems/boundary-of-binary-tree/

public class BoundaryOfBinaryTree {

  public List<Integer> boundaryOfBinaryTree(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    result.add(root.val);
    getBounds(root.left, result, true, false);
    getBounds(root.right, result, false, true);

    return result;
  }

  public void getBounds(TreeNode node, List<Integer> res, boolean leftBound, boolean rightBound) {
    if (node == null) {
      return;
    }

    if (leftBound) {
      res.add(node.val);
    }

    if (!leftBound && !rightBound && node.left == null && node.right == null) {
      res.add(node.val);
    }

    getBounds(node.left, res, leftBound, rightBound && node.right == null);
    getBounds(node.right, res, leftBound && node.left == null, rightBound);

    if (rightBound) {
      res.add(node.val);
    }
  }
}
