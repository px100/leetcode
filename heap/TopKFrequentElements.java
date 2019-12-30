import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// LC-347
// https://leetcode.com/problems/top-k-frequent-elements/

public class TopKFrequentElements {

  public List<Integer> topKFrequent(int[] nums, int k) {
    List<Integer> result = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return result;
    }

    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.merge(num, 1, Integer::sum);
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(map::get));

    for (int n : map.keySet()) {
      pq.offer(n);
      if (pq.size() > k) {
        pq.poll();
      }
    }

    while (!pq.isEmpty()) {
      result.add(0, pq.poll());
    }

    return result;
  }
}
