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
  public Node copyRandomListFaster(Node head) {
    if (head == null) {
      return null;
    }

    Node fake = new Node(-1);
    fake.next = head;

    // clone main node, insert new nodes as next's for original nodes
    Node n = head;
    while (n != null) {
      Node newNode = new Node(n.val);
      Node nNext = n.next;
      n.next = newNode;
      newNode.next = nNext;
      n = nNext;
    }

    // connect random pointers of clones to clones of randoms
    n = fake.next;
    while (n != null) {
      if (n.random != null) {
        n.next.random = n.random.next;
      }
      n = n.next.next;
    }

    // unwire original and new nodes, prepare the new head node for result
    n = fake.next;
    Node res = n.next;
    while (n != null) {
      Node nn = n.next;
      n.next = nn.next;
      if (n.next != null) {
        nn.next = nn.next.next;
      }
      n = n.next;
    }

    return res;
  }
}
