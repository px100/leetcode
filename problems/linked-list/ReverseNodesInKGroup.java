
// LC-25
// https://leetcode.com/problems/reverse-nodes-in-k-group/

public class ReverseNodesInKGroup {

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k == 1) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    int count = 0;

    ListNode p = head;
    while (p != null) {
      count++;
      if (count % k == 0) {
        prev = reverse(prev, p.next);
        p = prev.next;
      } else {
        p = p.next;
      }
    }

    return dummy.next;
  }

  private ListNode reverse(ListNode start, ListNode end) {
    ListNode last = start.next;
    ListNode curr = last.next;

    while (curr != end) {
      last.next = curr.next;
      curr.next = start.next;
      start.next = curr;
      curr = last.next;
    }

    return last;
  }
}
