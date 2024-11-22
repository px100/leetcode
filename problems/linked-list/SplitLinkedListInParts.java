
// LC-725
// https://leetcode.com/problems/split-linked-list-in-parts/

public class SplitLinkedListInParts {

  public ListNode[] splitListToParts(ListNode root, int k) {
    ListNode[] res = new ListNode[k];
    int count = 0;

    ListNode cur = root;
    while (cur != null) {
      count++;
      cur = cur.next;
    }

    cur = root;
    int size = count / k, extra = count % k;
    for (int i = 0; i < k && cur != null; i++) {
      res[i] = cur;
      // find tail
      for (int j = 0; j < size + (extra > 0 ? 1 : 0) - 1; j++) {
        cur = cur.next;
      }
      // cut the connection
      ListNode temp = cur.next;
      cur.next = null;
      cur = temp;
      // update the remaining space for extra nodes
      extra--;
    }

    return res;
  }
}
