
// LC-510
// https://leetcode.com/problems/inorder-successor-in-bst-ii/

public class InorderSuccessorInBSTII {

  private static class Node {

    public int val;
    public Node left;
    public Node right;
    public Node parent;
  }

  public Node inorderSuccessor(Node x) {
    if (x == null) {
      return x;
    }

    if (x.right != null) {
      Node successor = x.right;
      while (successor.left != null) {
        successor = successor.left;
      }

      return successor;
    }

    while (x.parent != null && x.parent.right == x) {
      x = x.parent;
    }

    return x.parent;
  }

  public Node inorderSuccessor2(Node x) {
    Node result = null;

    // case 1: right child is not null -> go down to get the next
    Node p = x.right;
    while (p != null) {
      result = p;
      p = p.left;
    }

    if (result != null) {
      return result;
    }

    // case 2: right child is null -> go up to the parent,
    // until the node is a left child, return the parent
    p = x;

    while (p != null) {
      if (p.parent != null && p.parent.left == p) {
        return p.parent;
      }
      p = p.parent;
    }

    return null;
  }
}
