import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// LC-1129
// https://leetcode.com/problems/shortest-path-with-alternating-colors/

public class ShortestPathWithAlternatingColors {

  private record Edge(int node, int color) {

  }

  public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
    Map<Integer, List<Edge>> map = new HashMap<>();
    for (int[] edge : redEdges) {
      map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new Edge(edge[1], 0));
    }
    for (int[] edge : blueEdges) {
      map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new Edge(edge[1], 1));
    }

    int[][] dist = new int[2][n];
    for (int[] d : dist) {
      Arrays.fill(d, Integer.MAX_VALUE);
    }
    dist[0][0] = 0;
    dist[1][0] = 0;

    Queue<Edge> queue = new LinkedList<>();
    queue.offer(new Edge(0, 0));
    queue.offer(new Edge(0, 1));

    while (!queue.isEmpty()) {
      for (int i = 0; i < queue.size(); i++) {
        Edge cur = queue.poll();
        int curNode = cur.node;
        int curColor = cur.color;
        int curDist = dist[curColor][curNode];
        for (Edge adj : map.getOrDefault(curNode, List.of())) {
          int adjNode = adj.node;
          int adjColor = adj.color;
          if (adjColor == curColor) {
            continue;
          }
          if (dist[adjColor][adjNode] != Integer.MAX_VALUE) {
            continue;
          }
          dist[adjColor][adjNode] = 1 + curDist;
          queue.offer(new Edge(adjNode, adjColor));
        }
      }
    }
    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
      int minDist = Math.min(dist[0][i], dist[1][i]);
      result[i] = minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
    return result;
  }
}
