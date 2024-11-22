import java.util.HashMap;
import java.util.Map;

public class MaximumPathQualityOfAGraph {

  private int max;

  public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    for (int[] edge : edges) {
      graph.computeIfAbsent(edge[0], k -> new HashMap<>()).put(edge[1], edge[2]);
      graph.computeIfAbsent(edge[1], k -> new HashMap<>()).put(edge[0], edge[2]);
    }
    int[] visited = new int[values.length];
    dfs(0, 0, maxTime, values, visited, graph);
    return max;
  }

  private void dfs(int cur, int value, int time, int[] values, int[] visited, Map<Integer, Map<Integer, Integer>> graph) {
    visited[cur]++;
    if (visited[cur] <= 1) {
      value += values[cur];
    }
    if (cur == 0) {
      max = Math.max(value, max);
    }
    for (Map.Entry<Integer, Integer> pair : graph.getOrDefault(cur, Map.of()).entrySet()) {
      int next = pair.getKey();
      int cost = pair.getValue();
      if (cost <= time) {
        dfs(next, value, time - cost, values, visited, graph);
      }
    }
    visited[cur]--;
  }
}
