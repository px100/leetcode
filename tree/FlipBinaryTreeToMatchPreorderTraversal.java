import java.util.ArrayList;
import java.util.List;

// LC-971
// https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/

public class FlipBinaryTreeToMatchPreorderTraversal {

  private int i = 0;
  private List<Integer> list = new ArrayList<>();

  public List<Integer> flipMatchVoyage(TreeNode root, int[] v) {
    if (!match(root, v)) {
      list.clear();
      list.add(-1);
    }

    return list;
  }

  private boolean match(TreeNode curr, int[] v) {
    if (curr == null) {
      return true;
    }
    if (curr.val != v[i]) {
      return false;
    }

    i++;

    if (!match(curr.left, v)) {
      list.add(curr.val);
      return match(curr.right, v) && match(curr.left, v);
    }

    return match(curr.right, v);
  }
}
