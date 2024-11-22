import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// LC-1019
// https://leetcode.com/problems/next-greater-node-in-linked-list/

public class NextGreaterNodeInLinkedList {

  public int[] nextLargerNodes(ListNode head) {
    if (head == null) {
      return null;
    }

    List<ListNode> nodes = new ArrayList<>();
    ListNode cur = head;
    while (cur != null) {
      nodes.add(cur);
      cur = cur.next;
    }

    int[] result = new int[nodes.size()];
    result[nodes.size() - 1] = 0;
    ListNode root = nodes.get(nodes.size() - 1);
    for (int i = nodes.size() - 2; i >= 0; i--) {
      cur = nodes.get(i);
      while (root != null && root.val <= cur.val) {
        root = root.next;
      }
      cur.next = root;
      result[i] = root == null ? 0 : root.val;
      root = cur;
    }

    return result;
  }

  public int[] nextLargerNodesMonotonicStack(ListNode head) {
    List<Integer> arr = new ArrayList<>();
    while (head != null) {
      arr.add(head.val);
      head = head.next;
    }
    // monotonic stack
    Stack<Integer> stack = new Stack<>();
    int[] result = new int[arr.size()];
    for (int i = arr.size() - 1; i >= 0; i--) {
      while ((!stack.isEmpty()) && (stack.peek() <= arr.get(i))) {
        stack.pop();
      }
      result[i] = stack.isEmpty() ? 0 : stack.peek();
      stack.push(arr.get(i));
    }

    return result;
  }
}
