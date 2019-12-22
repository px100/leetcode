
// LC-426
// https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

  private static class Node {

    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val, Node _left, Node _right) {
      val = _val;
      left = _left;
      right = _right;
    }
  }

  public Node treeToDoublyList(Node root) {
    if (root == null) {
      return root;
    }
    if (root.left == null && root.right == null) {
      link(root, root);
      return root;
    }

    // link them
    Node leftHead = treeToDoublyList(root.left); // left head
    Node rightHead = treeToDoublyList(root.right); // right head
    Node leftTail = leftHead != null ? leftHead.left : null;
    Node rightTail = rightHead != null ? rightHead.left : null;

    Node head = null;
    if (rightHead == null) {
      head = leftHead;
      link(leftTail, root);
      link(root, leftHead);
    } else if (leftHead == null) {
      head = root;
      link(root, rightHead);
      link(rightTail, root);
    } else { // both leftHead, rightHead exist
      head = leftHead;
      link(leftTail, root);
      link(root, rightHead);
      link(rightTail, leftHead);
    }

    return head;
  }

  private void link(Node nodeA, Node nodeB) {
    nodeA.right = nodeB;
    nodeB.left = nodeA;
  }

  ////////////////////////////////////////////////////////////////////////////

  private Node treeToDoublyList2(Node root) {
    if (root == null) {
      return null;
    }

    Node left = treeToDoublyList2(root.left);
    Node right = treeToDoublyList2(root.right);
    root.left = root;
    root.right = root;

    return join(join(left, root), right);
  }

  private Node join(Node left, Node right) {
    if (left == null) {
      return right;
    }
    if (right == null) {
      return left;
    }
    Node lastLeft = left.left;
    Node lastRight = right.left;

    lastLeft.right = right;
    right.left = lastLeft;
    lastRight.right = left;
    left.left = lastRight;

    return left;
  }
}
