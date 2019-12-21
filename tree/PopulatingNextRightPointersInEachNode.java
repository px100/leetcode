import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

public class PopulatingNextRightPointersInEachNode {

  private class Node {

    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }
  }

  public Node connect(Node root) {
    if (root == null) {
      return null;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Node temp = queue.remove();
        if (temp.left != null) {
          queue.add(temp.left);
        }
        if (temp.right != null) {
          queue.add(temp.right);
        }
        temp.next = i < size - 1 ? queue.peek() : null;
      }
    }

    return root;
  }

  public Node connect2(Node root) {
    if (root == null) {
      return root;
    }

    Node leftmost = root;
    while (leftmost.left != null) {
      Node head = leftmost;
      while (head != null) {
        head.left.next = head.right;
        if (head.next != null) {
          head.right.next = head.next.left;
        }
        head = head.next;
      }
      leftmost = leftmost.left;
    }

    return root;
  }

  private Node connectRec(Node root) {
    if (root == null) {
      return root;
    }

    connectRecHelper(root.left, root.right);

    return root;
  }

  private void connectRecHelper(Node left, Node right) {
    if (left == null) {
      return;
    }

    left.next = right;
    connectRecHelper(left.left, left.right);
    connectRecHelper(left.right, right.left);
    connectRecHelper(right.left, right.right);
  }
}
