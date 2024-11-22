
// LC-142
// https://leetcode.com/problems/linked-list-cycle-ii/

public class LinkedListCycleII {

  public ListNode detectCycle(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        ListNode search = head;
        while (search != slow) {
          slow = slow.next;
          search = search.next;
        }
        return slow;
      }
    }

    return null;
  }
}
