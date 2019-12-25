
// LC-2
// https://leetcode.com/problems/add-two-numbers/

public class AddTwoNumbers {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    }

    ListNode head = new ListNode(0);
    ListNode result = head;
    int carry = 0;

    while (l1 != null || l2 != null || carry > 0) {
      int total = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
      result.next = new ListNode(total % 10);

      carry = total / 10;

      l1 = l1 == null ? null : l1.next;
      l2 = l2 == null ? null : l2.next;
      result = result.next;
    }

    return head.next;
  }
}
