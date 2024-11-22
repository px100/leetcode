
// LC-430
// https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/

public class FlattenAMultilevelDoublyLinkedList {

  private static class Node {

    public int val;
    public Node prev;
    public Node next;
    public Node child;
  }

  public Node flatten(Node head) {
    // Copy the head pointer and use the copy to change the list structure
    Node current = head;
    dfs(null, current);

    return head;
  }

  private Node dfs(Node previous, Node current) {
    while (current != null) {
      // Push the next value to stack(implicitly via recursion)
      Node nextNode = current.next;
      current.prev = previous;
      if (previous != null) {
        previous.next = current;
        previous.child = null;
      }
      // Move the current pointer to child, if its available
      if (current.child != null) {
        current = dfs(current, current.child);
      }
      current.next = nextNode;
      previous = current;
      current = nextNode;
    }
    // return previous node as the current node should point to null
    return previous;
  }
}
