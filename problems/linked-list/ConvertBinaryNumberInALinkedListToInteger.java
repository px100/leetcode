
// LC-1290
// https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/

public class ConvertBinaryNumberInALinkedListToInteger {

  public int getDecimalValue(ListNode head) {
    int result = 0;
    while (head != null) {
      result = result * 2 + head.val;
      head = head.next;
    }

    return result;
  }
}
