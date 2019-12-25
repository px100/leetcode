import java.util.Stack;

// LC-445
// https://leetcode.com/problems/add-two-numbers-ii/

public class AddTwoNumbersII {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) {
      return java.util.Optional.ofNullable(l1).orElse(l2);
    }

    Stack<ListNode> stack1 = new Stack<>();
    Stack<ListNode> stack2 = new Stack<>();
    ListNode sentinel = new ListNode(0);
    int carry = 0;

    pushToStack(l1, stack1);
    pushToStack(l2, stack2);

    while (!stack1.isEmpty() || !stack2.isEmpty()) {
      int sum = carry;
      if (!stack1.isEmpty()) {
        sum += stack1.pop().val;
      }
      if (!stack2.isEmpty()) {
        sum += stack2.pop().val;
      }
      addToFront(sum % 10, sentinel);
      carry = sum / 10;
    }

    if (carry > 0) {
      addToFront(carry, sentinel);
    }

    return sentinel.next;
  }

  private void addToFront(int val, ListNode sentinel) {
    ListNode root = new ListNode(val);
    root.next = sentinel.next;
    sentinel.next = root;
  }

  private void pushToStack(ListNode head, Stack<ListNode> stack) {
    while (head != null) {
      stack.push(head);
      head = head.next;
    }
  }
}
