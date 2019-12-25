import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

// LC-23
// https://leetcode.com/problems/merge-k-sorted-lists/

public class MergeKSortedLists {

  // Min heap with priority queue
  public ListNode mergeKLists(ListNode[] lists) {
    ListNode result = new ListNode(0);
    ListNode temp = result;

    PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
    Arrays.stream(lists).filter(Objects::nonNull).forEach(pq::add);

    while (!pq.isEmpty()) {
      ListNode node = pq.poll();
      if (node.next != null) {
        pq.add(node.next);
      }
      temp.next = node;
      temp = temp.next;
    }

    return result.next;
  }

  // Divide and Conquer with extra space
  public ListNode mergeKListsDC(ListNode[] lists) {
    int listSize = lists.length;
    if (listSize == 0) {
      return null;
    }
    if (listSize == 1) {
      return lists[0];
    }

    ListNode result = null;
    Queue<ListNode> queue = new LinkedList<>();
    for (int i = 0; i < listSize; i += 2) {
      if (i == listSize - 1) {
        queue.offer(lists[listSize - 1]);
      } else {
        queue.offer(sort(lists[i], lists[i + 1]));
      }
    }

    while (!queue.isEmpty()) {
      int size = queue.size();
      switch (size) {
        case 1:
          result = queue.poll();
          break;
        case 2:
          result = sort(queue.poll(), queue.poll());
          break;
        default:
          for (int i = 0; i < size - 1; i += 2) {
            queue.offer(sort(queue.poll(), queue.poll()));
          }
          break;
      }
    }

    return result;
  }

  // Divide and conquer solution without extra space
  public ListNode mergeKListsDCFaster(ListNode[] lists) {
    int listSize = lists.length;
    if (listSize == 0) {
      return null;
    }
    if (listSize == 1) {
      return lists[0];
    }

    int count = 1;
    while (count < listSize) {
      for (int i = 0; i < listSize - count; i += count << 1) {
        lists[i] = sort(lists[i], lists[i + count]);
      }
      count <<= 1;
    }

    return lists[0];
  }

  // Helper sort
  private ListNode sort(ListNode x, ListNode y) {
    ListNode result = new ListNode(0);
    ListNode sorted = result;

    while (x != null && y != null) {
      if (x.val < y.val) {
        sorted.next = new ListNode(x.val);
        sorted = sorted.next;
        x = x.next;
      } else {
        sorted.next = new ListNode(y.val);
        sorted = sorted.next;
        y = y.next;
      }
    }
    sorted.next = x == null ? y : x;

    return result.next;
  }
}
