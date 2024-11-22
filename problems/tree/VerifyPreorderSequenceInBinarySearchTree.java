import java.util.Stack;

// https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree

public class VerifyPreorderSequenceInBinarySearchTree {

  public boolean verifyPreorder(int[] preorder) {
    if (preorder == null || preorder.length <= 1) {
      return true;
    }

    Stack<Integer> stack = new Stack<>();
    int max = Integer.MIN_VALUE;

    for (int num : preorder) {
      if (num < max) {
        return false;
      }

      while (!stack.isEmpty() && num > stack.peek()) {
        max = stack.pop();
      }

      stack.push(num);
    }

    return true;
  }

  public boolean verifyPreorder2(int[] preorder) {
    if (preorder == null || preorder.length <= 1) {
      return true;
    }

    int i = -1;
    int max = Integer.MIN_VALUE;

    for (int num : preorder) {
      if (num < max) {
        return false;
      }
      while (i >= 0 && num > preorder[i]) {
        max = preorder[i--];
      }
      preorder[++i] = num;
    }

    return true;
  }
}
