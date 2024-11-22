import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// LC-692
// https://leetcode.com/problems/top-k-frequent-words/

public class TopKFrequentWords {

  public List<String> topKFrequent(String[] words, int k) {
    List<String> result = new ArrayList<>();
    if (words == null || words.length == 0) {
      return result;
    }

    Map<String, Integer> map = new HashMap<>();
    for (String s : words) {
      map.merge(s, 1, Integer::sum);
    }

    PriorityQueue<String> heap = new PriorityQueue<>(
      (String w1, String w2) -> map.get(w1).equals(map.get(w2))
        ? w2.compareTo(w1)
        : map.get(w1) - map.get(w2));

    for (String s : map.keySet()) {
      heap.offer(s);
      if (heap.size() > k) {
        heap.poll();
      }
    }

    while (!heap.isEmpty()) {
      result.add(0, heap.poll());
    }

    return result;
  }
}
