import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

// LC-767
// https://leetcode.com/problems/reorganize-string/

public class ReorganizeString {

  public String reorganizeString(String s) {
    Map<Character, Integer> map = new HashMap<>();
    Map.Entry<Character, Integer> prev = null;

    PriorityQueue<Entry<Character, Integer>> pq =
      new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      map.merge(c, 1, Integer::sum);
    }
    map.entrySet().forEach(pq::offer);

    while (!pq.isEmpty()) {
      Map.Entry<Character, Integer> cur = pq.poll();
      if (prev != null && prev.getValue() > 0) {
        pq.offer(prev);
      }
      sb.append(cur.getKey());
      cur.setValue(cur.getValue() - 1);
      prev = cur;
    }

    return sb.length() == s.length() ? sb.toString() : "";
  }
}
