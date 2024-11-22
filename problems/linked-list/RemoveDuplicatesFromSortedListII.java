
// LC-82
// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

public class RemoveDuplicatesFromSortedListII {

  public ListNode deleteDuplicates(ListNode head) {
    return delete(head, Integer.MAX_VALUE);
  }

  private ListNode delete(ListNode head, int lastVal) {
    if (head == null) {
      return null;
    }

    if (head.val == lastVal) {
      return delete(head.next, head.val);
    }

    if (head.next == null) {
      return head;
    }

    if (head.val == head.next.val) {
      return delete(head.next.next, head.val);
    }

    head.next = delete(head.next, head.val);

    return head;
  }
}
