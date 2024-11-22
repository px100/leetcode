import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// LC-1046
// https://leetcode.com/problems/last-stone-weight/

public class LastStoneWeight {

  public int lastStoneWeight(int[] stones) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // maxHeap
    Arrays.stream(stones).forEach(pq::offer);

    while (pq.size() > 1) {
      pq.offer(pq.poll() - pq.poll());
    }

    return pq.peek();
  }
}
