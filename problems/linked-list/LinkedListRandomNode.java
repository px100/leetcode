
// LC-382
// https://leetcode.com/problems/linked-list-random-node/

public class LinkedListRandomNode {

  private static class Solution {

    private ListNode head;

    /**
     * @param head The linked list's head. Note that the head is guaranteed to be not null, so it
     *             contains at least one node.
     */
    public Solution(ListNode head) {
      this.head = head;
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
      int scope = 1;
      int result = 0;
      ListNode cur = this.head;
      while (cur != null) {
        if (Math.random() < 1.0 / scope) {
          result = cur.val;
        }
        scope++;
        cur = cur.next;
      }
      return result;
    }
  }
}
