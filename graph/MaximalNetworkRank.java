import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// LC-1615
// https://leetcode.com/problems/maximal-network-rank/

public class MaximalNetworkRank {

  // O(n^2)
  public int maximalNetworkRank(int n, int[][] roads) {
    boolean[][] connected = new boolean[n][n];
    int[] count = new int[n];

    for (int[] road : roads) {
      connected[road[0]][road[1]] = true;
      connected[road[1]][road[0]] = true;
      count[road[0]]++;
      count[road[1]]++;
    }

    int maxRank = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        maxRank = Math.max(maxRank, count[i] + count[j] - (connected[i][j] ? 1 : 0));
      }
    }

    return maxRank;
  }

  // O(n^2)
  // Graph Representation
  public int maximalNetworkRank5(int n, int[][] roads) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (int i = 0; i < n; i++) {
      graph.put(i, new HashSet<>());
    }
    for (int[] road : roads) {
      int from = road[0];
      int to = road[1];
      graph.get(from).add(to);
      graph.get(to).add(from);
    }

    int maxRank = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int result = graph.get(i).size() + graph.get(j).size();
        if (graph.get(i).contains(j)) {
          result--;
        }
        maxRank = Math.max(maxRank, result);
      }
    }

    return maxRank;
  }
}
