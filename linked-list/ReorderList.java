
// LC-143
// https://leetcode.com/problems/reorder-list/

public class ReorderList {

  // insert and reverse
  public void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }

    ListNode p = head;
    while (p.next != null) {
      ListNode tp = p.next;
      ListNode ttp;
      p.next = null;
      while (tp != null) {
        ttp = tp;
        tp = tp.next;
        ttp.next = p.next;
        p.next = ttp;
      }
      p = p.next;
    }
  }

  public void reorderList2(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return;
    }

    ListNode secondNode = middleNode(head); // find middle of the LL
    secondNode = reverseNode(secondNode); // reverse second half
    mergeNode(head, secondNode); // merge firstHalf and secondHalf
  }

  private ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    ListNode returnNode = slow.next;
    slow.next = null;

    return returnNode;
  }

  private ListNode reverseNode(ListNode head) {
    ListNode prev = null;
    ListNode cur = head;
    while (cur != null) {
      ListNode nxt = cur.next;
      cur.next = prev; // flip connection
      prev = cur;
      cur = nxt;
    }

    return prev;
  }

  private ListNode mergeNode(ListNode first, ListNode second) {
    ListNode dummyHead = new ListNode(-1);
    ListNode cur = dummyHead;
    while (first != null && second != null) {
      cur.next = first;
      first = first.next;
      cur = cur.next;
      cur.next = second;
      second = second.next;
      cur = cur.next;
    }

    if (first != null) {
      cur.next = first;
    }

    return dummyHead.next;
  }
}
