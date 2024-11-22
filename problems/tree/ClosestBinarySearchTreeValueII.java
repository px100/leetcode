import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/closest-binary-search-tree-value-ii/

public class ClosestBinarySearchTreeValueII {

  // O(n)
  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Stack<Integer> predecessor = new Stack<>();
    Stack<Integer> successor = new Stack<>();

    getPredecessor(root, target, predecessor); // returns the next smaller node to N.
    getSuccessor(root, target, successor); // returns the next larger node to N.

    for (int i = 0; i < k; i++) {
      if (predecessor.isEmpty()) {
        result.add(successor.pop());
      } else if (successor.isEmpty()) {
        result.add(predecessor.pop());
      } else if (Math.abs(predecessor.peek() - target) < Math.abs(successor.peek() - target)) {
        result.add(predecessor.pop());
      } else {
        result.add(successor.pop());
      }
    }

    return result;
  }

  private void getPredecessor(TreeNode root, double target, Stack<Integer> predecessor) {
    if (root == null) {
      return;
    }

    getPredecessor(root.left, target, predecessor);
    if (root.val > target) {
      return;
    }
    predecessor.push(root.val);
    getPredecessor(root.right, target, predecessor);
  }

  private void getSuccessor(TreeNode root, double target, Stack<Integer> successor) {
    if (root == null) {
      return;
    }

    getSuccessor(root.right, target, successor);
    if (root.val <= target) {
      return;
    }
    successor.push(root.val);
    getSuccessor(root.left, target, successor);
  }

  // O(k + lg(n))
  public List<Integer> closestKValuesBetter(TreeNode root, double target, int k) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Stack<TreeNode> lowerStack = new Stack<>();
    Stack<TreeNode> upperStack = new Stack<>();

    TreeNode p = root;
    while (p != null) {
      if (p.val < target) {
        lowerStack.push(p);
        p = p.right;
      } else {
        upperStack.push(p);
        p = p.left;
      }
    }

    for (int i = 0; i < k; i++) {
      if (lowerStack.isEmpty()) {
        TreeNode top = upperStack.pop();
        result.add(top.val);
        nextUpper(top.right, upperStack);
      } else if (upperStack.isEmpty()) {
        TreeNode top = lowerStack.pop();
        result.add(top.val);
        nextLower(top.left, lowerStack);
      } else if (upperStack.peek().val - target <= target - lowerStack.peek().val) {
        TreeNode top = upperStack.pop();
        result.add(top.val);
        nextUpper(top.right, upperStack);
      } else if (
        upperStack.isEmpty() || target - lowerStack.peek().val < upperStack.peek().val - target) {
        TreeNode top = lowerStack.pop();
        result.add(top.val);
        nextLower(top.left, lowerStack);
      }
    }

    return result;
  }

  private void nextUpper(TreeNode node, Stack<TreeNode> stack) {
    TreeNode p = node;
    while (p != null) {
      stack.push(p);
      p = p.left;
    }
  }

  private void nextLower(TreeNode node, Stack<TreeNode> stack) {
    TreeNode p = node;
    while (p != null) {
      stack.push(p);
      p = p.right;
    }
  }
}
