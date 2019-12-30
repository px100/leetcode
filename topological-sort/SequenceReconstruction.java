import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// LC-444
// https://leetcode.com/problems/sequence-reconstruction/

public class SequenceReconstruction {

  public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    Map<Integer, Integer> inDegree = new HashMap<>();

    for (int num : org) {
      map.put(num, new HashSet<>());
      inDegree.put(num, 0);
    }

    int n = org.length;
    int count = 0;
    for (List<Integer> seq : seqs) {
      count += seq.size();
      if (seq.size() >= 1 && (seq.get(0) <= 0 || seq.get(0) > n)) {
        return false;
      }
      for (int i = 1; i < seq.size(); i++) {
        if (seq.get(i) <= 0 || seq.get(i) > n) {
          return false;
        }
        if (map.get(seq.get(i - 1)).add(seq.get(i))) {
          inDegree.put(seq.get(i), inDegree.get(seq.get(i)) + 1);
        }
      }
    }

    if (count < n) {
      return false;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int key : map.keySet()) {
      if (inDegree.get(key) == 0) {
        queue.add(key);
      }
    }

    int cnt = 0;
    while (queue.size() == 1) {
      for (int next : map.get(queue.poll())) {
        inDegree.put(next, inDegree.get(next) - 1);
        if (inDegree.get(next) == 0) {
          queue.add(next);
        }
      }
      cnt++;
    }

    return cnt == org.length;
  }
}
