
// LC-148
// https://leetcode.com/problems/sort-list/

public class SortList {

  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode prev = null;
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    prev.next = null;

    ListNode left = sortList(head);
    ListNode right = sortList(slow);

    return merge(left, right);
  }

  private ListNode merge(ListNode l1, ListNode l2) {
    ListNode l = new ListNode(0);
    ListNode p = l;

    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
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
    }
    if (l2 != null) {
      p.next = l2;
    }

    return l.next;
  }
}
