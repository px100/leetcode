
// LC-707
// https://leetcode.com/problems/design-linked-list/

public class DesignLinkedList {

  private static class Node {

    Node next = null;
    int val;

    public Node(int v) {
      val = v;
    }
  }

  private static class MyLinkedList {

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public int get(int index) {
      if (index < 0 || index >= size) {
        return -1;
      }

      Node curr = head;
      for (int i = 0; i < index; i++) {
        curr = curr.next;
      }

      return curr.val;
    }

    public void addAtHead(int val) {
      Node n = new Node(val);
      if (size == 0) {
        head = n;
        tail = n;
      } else {
        n.next = head;
        head = n;
      }
      size++;
    }

    public void addAtTail(int val) {
      Node n = new Node(val);
      if (size == 0) {
        head = n;
      } else {
        tail.next = n;
      }
      tail = n;
      size++;
    }

    public void addAtIndex(int index, int val) {
      if (index > size) {
        return;
      }

      // if index is negative, the node will be inserted at the head of the list
      if (index <= 0) {
        addAtHead(val);
      } else if (index == size) {
        addAtTail(val);
      } else {
        Node n = new Node(val);
        Node curr = head;
        for (int i = 0; i < index - 1; i++) {
          curr = curr.next;
        }
        n.next = curr.next;
        curr.next = n;
        size++;
      }
    }

    public void deleteHead() {
      if (size > 0) {
        head = head.next;
        size--;
        if (size == 0) {
          tail = null;
        }
      }
    }

    public void deleteAtIndex(int index) {
      if (index < 0 || index >= size) {
        return;
      }

      if (index == 0) {
        deleteHead();
      } else {
        Node curr = head;
        for (int i = 0; i < index - 1; i++) {
          curr = curr.next;
        }
        if (curr.next == tail) {
          tail = curr;
        }
        curr.next = curr.next.next;
        size--;
      }
    }
  }
}
