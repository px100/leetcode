import java.util.LinkedList;
import java.util.Queue;

// LC-606
// https://leetcode.com/problems/construct-string-from-binary-tree/

public class ConstructStringFromBinaryTree {

  public String tree2str(TreeNode t) {
    if (t == null) {
      return "";
    }

    Queue<String> str = new LinkedList<>();
    tree2str(t, str);

    return String.join("", str);
  }

  private void tree2str(TreeNode t, Queue<String> str) {
    if (t == null) {
      return;
    }

    boolean child = t.left == null && t.right == null;
    str.add("" + t.val);

    if (t.left == null && !child) {
      str.add("()");
    }

    if (t.left != null) {
      str.add("(");
      tree2str(t.left, str);
      str.add(")");
    }

    if (t.right != null) {
      str.add("(");
      tree2str(t.right, str);
      str.add(")");
    }
  }
}
