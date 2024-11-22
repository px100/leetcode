
// LC-817
// https://leetcode.com/problems/linked-list-components/

public class LinkedListComponents {

  public int numComponents(ListNode head, int[] G) {
    boolean[] map = new boolean[1000 + 1];
    for (int i : G) {
      map[i] = true;
    }

    boolean pre = false;
    int result = 0;
    while (head != null) {
      if (!pre && map[head.val]) {
        result++;
        pre = true;
      } else if (pre && !map[head.val]) {
        pre = false;
      }
      head = head.next;
    }

    return result;
  }
}
