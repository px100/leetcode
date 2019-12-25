
// LC-19
// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

public class RemoveNthNodeFromEndOfList {

  // Next of current pointer has to be removed, when fast pointer reaches the end
  // since the difference between current and fast pointers is (n+1).
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return null;
    }

    ListNode slow = head;
    ListNode fast = head;

    while (fast != null) {
      fast = fast.next;
      if (n == -1) {
        slow = slow.next;
      } else {
        n--;
      }
    }

    if (head == slow && n >= 0) {
      head = head.next;
    }
    if (slow != null && slow.next != null) {
      slow.next = slow.next.next;
    }

    return head;
  }
}
