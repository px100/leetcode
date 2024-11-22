import java.util.HashMap;
import java.util.Map;

// LC-1171
// https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/

public class RemoveZeroSumConsecutiveNodesFromLinkedList {

  public ListNode removeZeroSumSublistsRecursive(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      return head.val == 0 ? null : head;
    }

    ListNode p = removeZeroSumSublistsRecursive(head.next);
    head.next = p;
    int sum = head.val;
    while (p != null) {
      if (sum == 0) {
        return p;
      }
      sum += p.val;
      p = p.next;
    }
    if (sum == 0) {
      return null;
    }

    return head;
  }

  public ListNode removeZeroSumSublists(ListNode head) {
    Map<Integer, ListNode> map = new HashMap<>();

    boolean found = true;
    while (found) {
      found = false;
      ListNode p = head;
      int sum = 0;
      while (p != null) {
        sum += p.val;
        if (sum == 0) {
          head = p.next;
          map.clear();
          found = true;
          break;
        } else if (map.containsKey(sum)) {
          map.get(sum).next = p.next;
          map.clear();
          found = true;
          break;
        }
        map.put(sum, p);
        p = p.next;
      }
    }

    return head;
  }

  // use hashmap to record the presum,
  // if presum exists before, then sum == 0 must be in-between
  // then set prev.next = curr.next
  public ListNode removeZeroSumSublistsMap(ListNode head) {
    ListNode dummy = new ListNode(0);
    Map<Integer, ListNode> presumMap = new HashMap<>();
    dummy.next = head;
    ListNode curr = dummy;
    int prevSum = 0;

    while (curr != null) {
      prevSum += curr.val;
      presumMap.put(prevSum, curr);
      curr = curr.next;
    }

    curr = dummy;
    prevSum = 0;
    while (curr != null) {
      prevSum += curr.val;
      if (presumMap.containsKey(prevSum)) {
        curr.next = presumMap.get(prevSum).next;
      }
      curr = curr.next;
    }

    return dummy.next;
  }
}
