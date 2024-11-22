
// LC-328
// https://leetcode.com/problems/odd-even-linked-list/

public class OddEvenLinkedList {

  public ListNode oddEvenList(ListNode head) {
    if (head == null) {
      return head;
    }

    ListNode odd = head;
    ListNode even = head.next;
    ListNode evenHead = even;

    while (even != null && even.next != null) {
      odd.next = even.next;
      odd = odd.next;
      even.next = odd.next;
      even = even.next;
    }
    odd.next = evenHead;

    return head;
  }
}
