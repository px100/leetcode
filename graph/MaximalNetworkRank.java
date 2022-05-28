import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
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
    for (int[] road : roads) {
      graph.computeIfAbsent(road[0], k -> new HashSet<>()).add(road[1]);
      graph.computeIfAbsent(road[1], k -> new HashSet<>()).add(road[0]);
    }

    int maxRank = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        Set<Integer> connectedI = graph.get(i);
        Set<Integer> connectedJ = graph.get(j);
        if (Objects.isNull(connectedI) || Objects.isNull(connectedJ)) {
          continue;
        }
        int countI = connectedI.size();
        int countJ = connectedJ.size();
        maxRank = Math.max(maxRank, countI + countJ - (connectedI.contains(j) ? 1 : 0));
      }
    }

    return maxRank;
  }
}
