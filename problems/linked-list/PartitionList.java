
// LC-86
// https://leetcode.com/problems/partition-list/

public class PartitionList {

  public ListNode partition(ListNode head, int x) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode leftDummy = new ListNode(0);
    ListNode rightDummy = new ListNode(0);

    ListNode leftLast = leftDummy;
    ListNode rightLast = rightDummy;

    ListNode cur = head;

    while (cur != null) {
      // add to left
      if (cur.val < x) {
        leftLast.next = cur;
        cur = cur.next;
        leftLast = leftLast.next;
        leftLast.next = null;
      } else {
        // add to right
        rightLast.next = cur;
        cur = cur.next;
        rightLast = rightLast.next;
        rightLast.next = null;
      }
    }

    leftLast.next = rightDummy.next;

    return leftDummy.next;
  }
}
