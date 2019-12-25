
// LC-203
// https://leetcode.com/problems/remove-linked-list-elements/

public class RemoveLinkedListElements {

  public ListNode removeElements(ListNode head, int val) {
    if (head == null) {
      return null;
    }

    ListNode temp = new ListNode(0);
    temp.next = head;

    ListNode prev = temp;
    while (prev.next != null) {
      if (prev.next.val == val) {
        prev.next = prev.next.next;
      } else {
        prev = prev.next;
      }
    }

    return temp.next;
  }
}
