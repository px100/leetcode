import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// LC-1514
// https://leetcode.com/problems/path-with-maximum-probability/

public class PathWithMaximumProbability {

  private record Node(int node, double dist) {

  }

  public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
    Map<Integer, List<Node>> graph = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0];
      int v = edges[i][1];
      double minusLog = -Math.log(succProb[i]);
      graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Node(v, minusLog));
      graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Node(u, minusLog));
    }

    double[] dist = new double[n];
    Arrays.fill(dist, -1.0);

    PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a.dist));
    pq.offer(new Node(start, 0.0));

    Set<Integer> visited = new HashSet<>();
    visited.add(start);

    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      if (dist[cur.node] != -1.0) {
        continue;
      }
      if (cur.dist > dist[cur.node]) {
        dist[cur.node] = cur.dist;
      }
      if (cur.node == end) {
        return Math.exp(-dist[cur.node]);
      }
      visited.add(cur.node);
      for (Node next : graph.getOrDefault(cur.node, List.of())) {
        if (!visited.contains(next.node)) {
          pq.offer(new Node(next.node, dist[cur.node] + next.dist));
        }
      }
    }
    return 0.0;
  }
}
