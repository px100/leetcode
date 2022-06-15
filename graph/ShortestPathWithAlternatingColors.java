import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// LC-1129
// https://leetcode.com/problems/shortest-path-with-alternating-colors/

public class ShortestPathWithAlternatingColors {

  private static final int RED = 0;
  private static final int BLUE = 1;
  private static final int UNCOLORED = 2;

  private record Edge(int dest, int color) {

  }

  // BFS-1
  public int[] shortestAlternatingPaths1(int n, int[][] redEdges, int[][] blueEdges) {
    Map<Integer, List<Edge>> graph = new HashMap<>();
    for (int[] edge : redEdges) {
      graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new Edge(edge[1], RED));
    }
    for (int[] edge : blueEdges) {
      graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new Edge(edge[1], BLUE));
    }

    int[] answer = new int[n];
    Arrays.fill(answer, -1);

    Queue<int[]> queue = new LinkedList<>();
    Set<Edge> visited = new HashSet<>();
    queue.offer(new int[]{0, 0, UNCOLORED}); // {destination, currDistance, prevEdgeColor}

    while (!queue.isEmpty()) {
      int[] item = queue.poll();
      int node = item[0];
      int curDistance = item[1];
      int prevEdgeColor = item[2];

      if (answer[node] == -1) {
        answer[node] = curDistance;
      }

      int nextDistance = 1 + curDistance;

      for (Edge edge : graph.getOrDefault(node, List.of())) {
        if (visited.contains(edge)) {
          continue;
        }

        if (prevEdgeColor == UNCOLORED) { // we can take and edge color starting from node 0
          queue.offer(new int[]{edge.dest, nextDistance, edge.color});
          visited.add(edge);
        }

        if (prevEdgeColor == RED && edge.color == BLUE) { // if we arrived from a RED edge, we can only go on BLUE edges
          queue.offer(new int[]{edge.dest, nextDistance, edge.color});
          visited.add(edge);
        }

        if (prevEdgeColor == BLUE && edge.color == RED) { // if we arrived from a BLUE edge, we can only go on RED edges
          queue.offer(new int[]{edge.dest, nextDistance, edge.color});
          visited.add(edge);
        }
      }
    }
    return answer;
  }
}
