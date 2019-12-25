
// LC-206
// https://leetcode.com/problems/reverse-linked-list/

public class ReverseLinkedList {

  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode prev = null;
    ListNode cur = head;
    ListNode next = null;

    while (cur != null) {
      next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
    }

    return prev;
  }
}
