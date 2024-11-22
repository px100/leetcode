
// LC-61
// https://leetcode.com/problems/rotate-list/

public class RotateList {

  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode cur = head;
    int size = 0;
    while (cur != null) {
      size++;
      cur = cur.next;
    }

    for (int i = 0; i < k % size; i++) {
      cur = head;
      while (cur.next.next != null) {
        cur = cur.next;
      }
      cur.next.next = head;
      head = cur.next;
      cur.next = null;
    }

    return head;
  }

  public ListNode rotateRight2(ListNode head, int k) {
    if (head == null || head.next == null) {
      return head;
    }

    int size = 0;
    ListNode cur = head;
    ListNode last = null;
    while (cur != null) {
      last = cur;
      cur = cur.next;
      size++;
    }

    if (k % size == 0) {
      return head;
    }

    // find the last node and set its next to null
    int steps = size - k % size - 1;

    cur = head;
    while (--steps >= 0) {
      cur = cur.next;
    }

    ListNode result = cur.next;
    cur.next = null;
    last.next = head;

    return result;
  }
}
