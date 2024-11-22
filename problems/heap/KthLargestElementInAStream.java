import java.util.PriorityQueue;

// LC-703
// https://leetcode.com/problems/kth-largest-element-in-a-stream/

public class KthLargestElementInAStream {

  class KthLargest {

    int k;
    PriorityQueue<Integer> queue;

    public KthLargest(int k, int[] nums) {
      this.queue = new PriorityQueue<>();
      this.k = k;

      for (int i : nums) {
        queue.offer(i);
        if (queue.size() > k) {
          queue.poll();
        }
      }
    }

    public int add(int val) {
      queue.offer(val);
      if (queue.size() > k) {
        queue.poll();
      }

      return queue.peek();
    }
  }
}
