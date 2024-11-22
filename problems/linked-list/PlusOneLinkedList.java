import java.util.Stack;

// LC-369
// https://leetcode.com/problems/plus-one-linked-list/

public class PlusOneLinkedList {

  public ListNode plusOne(ListNode head) {
    ListNode h2 = reverse(head);

    ListNode p = h2;

    while (p != null) {
      if (p.val + 1 <= 9) {
        p.val = p.val + 1;
        break;
      } else {
        p.val = 0;
        if (p.next == null) {
          p.next = new ListNode(1);
          break;
        }
        p = p.next;
      }
    }

    return reverse(h2);
  }

  private ListNode reverse(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode prev = null;
    ListNode cur = head;
    ListNode next = null;

    while (cur != null) {
      next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
    }

    return prev;
  }

  public ListNode plusOneStack(ListNode head) {
    Stack<ListNode> stack = new Stack<>();
    ListNode node = head;
    while (node != null) {
      stack.push(node);
      node = node.next;
    }

    int carry = 0;
    if (stack.peek().val != 9) {
      stack.peek().val++;
      return head;
    } else {
      stack.pop().val = 0;
      carry = 1;
      while (!stack.isEmpty()) {
        if (stack.peek().val != 9) {
          stack.peek().val++;
          return head;
        } else {
          stack.pop().val = 0;
        }
      }
      node = new ListNode(carry);
      node.next = head;
      return node;
    }
  }
}
