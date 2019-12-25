
// LC-83
// https://leetcode.com/problems/remove-duplicates-from-sorted-list/

public class RemoveDuplicatesFromSortedList {

  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode cur = head;

    while (cur.next != null) {
      if (cur.next.val == cur.val) {
        cur.next = cur.next.next;
      } else {
        cur = cur.next;
      }
    }

    return head;
  }
}
