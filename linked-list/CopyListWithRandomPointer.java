import java.util.HashMap;
import java.util.Map;

// LC-138
// https://leetcode.com/problems/copy-list-with-random-pointer/

public class CopyListWithRandomPointer {

  private static class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  Map<Node, Node> map = new HashMap<>();

  // O(n) time and space
  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }

    // create copy of all the nodes with next and random fields as null,
    // then put the original node and the copy into the map.

    Node cur = new Node(head.val);
    map.put(head, cur);

    cur.next = copyRandomList(head.next);

    // set the random field of the current copy by getting the target node from the map
    cur.random = map.get(head.random);

    return cur;
  }

  // O(n) time - 3 passes
  // O(1) space
  // re-use original list to store cloned nodes by doing
  // original_next = original_node.next;
  // original_node.next = cloned_node;
  // cloned_node.next = original_next
  public Node copyRandomList2(Node head) {
    if (head == null) {
      return null;
    }

    createNewNodes(head);
    copyRandom(head);
    Node newHead = head.next;
    split(head);
    return newHead;
  }

  // clone main node, insert new nodes as next's for original nodes
  private void createNewNodes(Node head) {
    while (head != null) {
      Node copy = new Node(head.val);
      copy.next = head.next;
      head.next = copy;
      head = copy.next;
    }
  }

  // connect random pointers of clones to clones of randoms
  private void copyRandom(Node head) {
    while (head != null) {
      if (head.random != null) {
        head.next.random = head.random.next;
      }
      head = head.next.next;
    }
  }

  // unwire original and new nodes, prepare the new head node for result
  private void split(Node head) {
    while (head != null) {
      Node next = head.next.next;
      Node newHead = head.next;
      head.next = next;
      head = head.next;
      if (next != null) {
        newHead.next = next.next;
      }
    }
  }
}
