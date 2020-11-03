
// LC-1474
// https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/

public class DeleteNNodesAfterMNodesOfALinkedList {

  public static ListNode deleteNodes(ListNode head, int m, int n) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode prev = null;
    ListNode cur = head;
    ListNode next;

    // skip m nodes
    for (int i = 0; cur != null && i < m; i++) {
      prev = cur;
      cur = cur.next;
    }

    // delete next n nodes
    for (int i = 0; cur != null && i < n; i++) {
      next = cur.next;
      cur = next;
    }

    // link remaining nodes with last Node
    prev.next = cur;

    // recur for remaining nodes
    deleteNodes(cur, m, n);

    return prev;
  }
}
