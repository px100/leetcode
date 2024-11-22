import java.util.Stack;

// LC-445
// https://leetcode.com/problems/add-two-numbers-ii/

public class AddTwoNumbersII {

  public ListNode addTwoNumbersWithoutStacks(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }

    int len1 = 0;
    int len2 = 0;

    ListNode cur1 = l1;
    ListNode cur2 = l2;

    while (cur1 != null) {
      len1++;
      cur1 = cur1.next;
    }

    while (cur2 != null) {
      len2++;
      cur2 = cur2.next;
    }

    if (len1 < len2) {
      ListNode tmp = l1;
      l1 = l2;
      l2 = tmp;
    }

    cur1 = l1;
    cur2 = l2;

    int offset = Math.abs(len1 - len2);
    while (offset > 0) {
      offset--;
      cur1 = cur1.next;
    }

    while (cur1 != null) {
      cur1.val += cur2.val;
      cur1 = cur1.next;
      cur2 = cur2.next;
    }

    cur1 = l1;
    cur2 = l1.next;

    while (cur2 != null) {
      while (cur2 != null && cur2.val < 9) {
        cur1 = cur1.next;
        cur2 = cur2.next;
      }
      if (cur2 == null) {
        break;
      }
      while (cur2 != null && cur2.val == 9) {
        cur2 = cur2.next;
      }
      if (cur2 == null) {
        break;
      }
      if (cur2.val > 9) {
        cur1.val++;
        cur1 = cur1.next;
        while (cur1 != cur2) {
          cur1.val = 0;
          cur1 = cur1.next;
        }
        cur1.val %= 10;
      }
      cur1 = cur2;
      cur2 = cur1.next;
    }

    if (l1.val > 9) {
      ListNode newNode = new ListNode(1);
      newNode.next = l1;
      l1.val %= 10;
      l1 = newNode;
    }

    return l1;
  }

  public ListNode addTwoNumbersWithStacks(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
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
