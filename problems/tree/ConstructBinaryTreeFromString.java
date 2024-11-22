import java.util.Stack;

// LC-536
// https://leetcode.com/problems/construct-binary-tree-from-string/

public class ConstructBinaryTreeFromString {

  public TreeNode str2tree(String s) {
    if (s.isBlank()) {
      return null;
    }

    int pos = s.indexOf('(');
    if (pos == -1) {
      return new TreeNode(Integer.parseInt(s));
    }

    Stack<Character> stack = new Stack<>();
    stack.push(s.charAt(pos));
    int q = pos + 1;
    while (q < s.length()) {
      if (s.charAt(q) == ')') {
        if (!stack.empty()) {
          stack.pop();
        }
      } else if (s.charAt(q) == '(') {
        stack.push(s.charAt(q));
      }
      q++;
      if (stack.empty()) {
        break;
      }
    }

    TreeNode root = new TreeNode(-1);
    root.val = Integer.parseInt(s.substring(0, pos));
    root.left = str2tree(s.substring(pos + 1, q - 1));
    if (q + 1 < s.length()) {
      root.right = str2tree(s.substring(q + 1, s.length() - 1));
    }

    return root;
  }
}
