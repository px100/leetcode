
// LC-147
// https://leetcode.com/problems/insertion-sort-list/

public class InsertionSortList {

  public ListNode insertionSortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode sortedListHead = null;

    while (head != null) {
      ListNode oldHead = head;
      head = head.next;
      oldHead.next = null;
      sortedListHead = insertInSortedList(oldHead, sortedListHead);
    }

    return sortedListHead;
  }

  private ListNode insertInSortedList(ListNode element, ListNode sortedListHead) {
    if (sortedListHead == null) {
      return element;
    }

    // if the element we are processing is the smallest
    if (element.val < sortedListHead.val) {
      //element needs to go in front
      element.next = sortedListHead;
      return element;
    }

    //otherwise the element that we are processing will go somewhere in the middle or also may be at the very end
    ListNode temp = sortedListHead;

    // find the appropriate place in the list
    // use temp.next != null instead of temp != null
    // because we want to hold the reference of place after which this element will be inserted
    // otherwise if we check on temp != null,
    // we will loose that reference and we may have to use another previous pointer in that case
    while (temp.next != null && element.val >= temp.next.val) {
      temp = temp.next;
    }

    // the elements place is found, attach it and return
    element.next = temp.next;
    temp.next = element;

    return sortedListHead;
  }
}
