
// LC-1086
// https://leetcode.com/problems/high-five

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HighFive {

  public int[][] highFive(int[][] items) {
    Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    for (int[] item : items) {
      int id = item[0];
      int score = item[1];
      PriorityQueue<Integer> queue = map.get(id);
      if (queue == null) {
        queue = new PriorityQueue<>(5);
        map.put(id, queue);
      }
      queue.offer(score);
      if (queue.size() > 5) {
        queue.poll();
      }
    }

    int index = 0;
    int[][] res = new int[map.size()][2];
    for (int id : map.keySet()) {
      PriorityQueue<Integer> queue = map.get(id);
      int sum = 0;
      while (!queue.isEmpty()) {
        sum += queue.poll();
      }
      res[index][0] = id;
      res[index][1] = sum / 5;
      index++;
    }
    return res;
  }
}
