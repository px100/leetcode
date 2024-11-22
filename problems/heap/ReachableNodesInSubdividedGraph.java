import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// LC-882
// https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/

public class ReachableNodesInSubdividedGraph {

  public int reachableNodes(int[][] edges, int M, int N) {
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    Map<Integer, Integer> visited = new HashMap<>();

    for (int[] edge : edges) {
      graph.putIfAbsent(edge[0], new HashMap<>());
      graph.putIfAbsent(edge[1], new HashMap<>());
      graph.get(edge[0]).put(edge[1], edge[2]);
      graph.get(edge[1]).put(edge[0], edge[2]);
    }

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    pq.offer(new int[]{0, M});
    visited.put(0, M);

    while (!pq.isEmpty()) {
      int cur = pq.peek()[0];
      int d = pq.peek()[1];
      pq.poll();
      if (graph.containsKey(cur)) {
        for (int next : graph.get(cur).keySet()) {
          int left = d - graph.get(cur).get(next) - 1;
          if (visited.getOrDefault(next, 0) <= left) {
            if (visited.getOrDefault(next, 0) < left) {
              pq.offer(new int[]{next, left});
            }
            visited.put(next, left);
          }
        }
      }
    }

    int sum = 0;
    for (int[] edge : edges) {
      sum += Math.min(edge[2], visited.getOrDefault(edge[0], 0) + visited.getOrDefault(edge[1], 0));
    }

    return visited.size() + sum;
  }
}
