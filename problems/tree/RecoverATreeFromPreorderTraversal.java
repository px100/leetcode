
// LC-1028
// https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/

public class RecoverATreeFromPreorderTraversal {

  private int curIdx = 0;

  public TreeNode recoverFromPreorder(String S) {
    return check(S, 0);
  }

  private TreeNode check(String s, int depth) {
    // check level is correct
    int num = 0;
    while (curIdx + num < s.length() && s.charAt(curIdx + num) == '-') {
      num++;
    }

    if (num != depth) {
      return null;
    }

    // get val
    int next = curIdx + num;
    while (next < s.length() && s.charAt(next) != '-') {
      next++;
    }
    int val = Integer.parseInt(s.substring(curIdx + num, next));
    curIdx = next;

    // construct
    TreeNode root = new TreeNode(val);
    root.left = check(s, depth + 1);
    root.right = check(s, depth + 1);

    return root;
  }
}
