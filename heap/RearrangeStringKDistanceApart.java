import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

// LC-358
// https://leetcode.com/problems/rearrange-string-k-distance-apart/

public class RearrangeStringKDistanceApart {

  // Greedy Using Heap
  // Time : O(N*log(26))
  public String rearrangeString(String str, int k) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < str.length(); i++) {
      map.merge(str.charAt(i), 1, Integer::sum);
    }

    PriorityQueue<Entry<Character, Integer>> maxHeap =
      new PriorityQueue<>(1, (e1, e2) -> e2.getValue() - e1.getValue());

    map.entrySet().forEach(maxHeap::offer);
    Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
    StringBuilder result = new StringBuilder();

    while (!maxHeap.isEmpty()) {
      Map.Entry<Character, Integer> entry = maxHeap.poll();
      result.append(entry.getKey());
      entry.setValue(entry.getValue() - 1);
      waitQueue.offer(entry);
      if (waitQueue.size() >= k) {
        Map.Entry<Character, Integer> unfreezeEntry = waitQueue.poll();
        if (unfreezeEntry.getValue() > 0) {
          maxHeap.offer(unfreezeEntry);
        }
      }
    }

    return result.length() == str.length() ? result.toString() : "";
  }

  // Greedy Using Array
  // Time : O(N*26)
  public String rearrangeString2(String str, int k) {
    int[] count = new int[26];
    int[] nextValid = new int[26];
    for (int i = 0; i < str.length(); i++) {
      count[str.charAt(i) - 'a']++;
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      int nextCandidate = findNextValid(count, nextValid, i);
      if (nextCandidate == -1) {
        return "";
      } else {
        result.append((char) ('a' + nextCandidate));
        count[nextCandidate]--;
        nextValid[nextCandidate] += k;
      }
    }

    return result.toString();
  }

  private int findNextValid(int[] count, int[] nextValid, int index) {
    int nextCandidate = -1;
    int max = 0;
    for (int i = 0; i < count.length; i++) {
      if (count[i] > max && index >= nextValid[i]) {
        max = count[i];
        nextCandidate = i;
      }
    }

    return nextCandidate;
  }
}
