
// LC-21
// https://leetcode.com/problems/merge-two-sorted-lists/

public class MergeTwoSortedLists {

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else if (l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }
  }

  public ListNode mergeTwoListsIterative(ListNode l1, ListNode l2) {
    ListNode head = new ListNode(0);
    ListNode p = head;
    while ((l1 != null) && (l2 != null)) {
      if (l1.val < l2.val) {
        p.next = l1;
        l1 = l1.next;
      } else {
        p.next = l2;
        l2 = l2.next;
      }
      p = p.next;
    }

    if (l1 != null) {
      p.next = l1;
    } else if (l2 != null) {
      p.next = l2;
    }

    return head.next;
  }
}