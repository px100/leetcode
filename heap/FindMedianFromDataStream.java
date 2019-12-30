import java.util.Comparator;
import java.util.PriorityQueue;

// LC-295
// https://leetcode.com/problems/find-median-from-data-stream/
// https://leetcode.com/problems/find-median-from-data-stream/solution/

public class FindMedianFromDataStream {

  class MedianFinder {

    /**
     * initialize your data structure here.
     */
    PriorityQueue<Integer> large; // a min Heap stores all larger nums
    PriorityQueue<Integer> small; // a max Heap stores all smaller nums

    public MedianFinder() {
      small = new PriorityQueue<>(Comparator.comparingInt(a -> a));
      large = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
      large.offer(num);
      small.offer(large.poll());
      if (small.size() > large.size()) {
        large.offer(small.poll());
      }
    }

    public double findMedian() {
      return large.size() > small.size()
        ? (double) large.peek()
        : (double) (large.peek() + small.peek()) / 2;
    }
  }
}
