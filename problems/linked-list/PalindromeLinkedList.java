
// LC-234
// https://leetcode.com/problems/palindrome-linked-list/

public class PalindromeLinkedList {

  private ListNode ref;

  public boolean isPalindrome(ListNode head) {
    ref = head;
    return check(head);
  }

  private boolean check(ListNode node) {
    if (node == null) {
      return true;
    }

    boolean result = check(node.next);
    boolean sameVal = ref.val == node.val;
    ref = ref.next;

    return result && sameVal;
  }
}
