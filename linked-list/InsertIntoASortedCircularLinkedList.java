
// LC-708
// https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/

public class InsertIntoASortedCircularLinkedList {

  private static class Node {

    public int val;
    public Node next;

    public Node() {
    }

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }

  public Node insert(Node head, int insertVal) {
    Node node = new Node(insertVal, null);
    if (head == null) {
      node.next = node;
      return node;
    }

    Node cur = head;
    while (cur.next != head) {
      if (insertVal < cur.val || insertVal > cur.next.val) {
        if (cur.val > cur.next.val && (insertVal >= cur.val || insertVal <= cur.next.val)) {
          node.next = cur.next;
          cur.next = node;
          return head;
        }
      } else {
        node.next = cur.next;
        cur.next = node;
        return head;
      }
      cur = cur.next;
    }

    node.next = cur.next;
    cur.next = node;

    return head;
  }
}
