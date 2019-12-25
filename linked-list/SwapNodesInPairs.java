
// LC-24
// https://leetcode.com/problems/swap-nodes-in-pairs/

public class SwapNodesInPairs {

  public ListNode swapPairs(ListNode head) {
    return swap(head);
  }

  private ListNode swap(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode next = head.next;

    head.next = next.next;
    next.next = head;
    head.next = swap(head.next);

    return next;
  }
}
