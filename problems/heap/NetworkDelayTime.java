import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// LC-743
// https://leetcode.com/problems/network-delay-time/

public class NetworkDelayTime {

  // Dijkstra - Heap
  // O(E*log(E))
  public int networkDelayTimeDijkstraHeap(int[][] times, int N, int K) {
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int[] edge : times) {
      if (!graph.containsKey(edge[0])) {
        graph.put(edge[0], new ArrayList<>());
      }
      graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
    }

    PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(info -> info[0]));
    heap.offer(new int[]{0, K});

    Map<Integer, Integer> distanceMap = new HashMap<>();
    while (!heap.isEmpty()) {
      int[] info = heap.poll();
      int d = info[0];
      int node = info[1];
      if (!distanceMap.containsKey(node)) {
        distanceMap.put(node, d);
        if (graph.containsKey(node)) {
          for (int[] edge : graph.get(node)) {
            int neighbor = edge[0];
            int d2 = edge[1];
            if (!distanceMap.containsKey(neighbor)) {
              heap.offer(new int[]{d + d2, neighbor});
            }
          }
        }
      }
    }

    if (distanceMap.size() != N) {
      return -1;
    }

    int ans = 0;
    for (int distance : distanceMap.values()) {
      ans = Math.max(ans, distance);
    }

    return ans;
  }
}
