import java.util.Arrays;

// LC-1334
// https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/

public class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {

  public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    int[][] adj = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(adj[i], Integer.MAX_VALUE >> 1);
      adj[i][i] = 0;
    }

    for (int[] edge : edges) {
      adj[edge[0]][edge[1]] = Math.min(adj[edge[0]][edge[1]], edge[2]);
      adj[edge[1]][edge[0]] = Math.min(adj[edge[1]][edge[0]], edge[2]);
    }

    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (adj[i][j] > adj[i][k] + adj[k][j]) {
            adj[i][j] = adj[i][k] + adj[k][j];
          }
        }
      }
    }

    int result = -1;
    int minReachable = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      int reachable = 0;
      for (int j = 0; j < n; j++) {
        if (adj[i][j] <= distanceThreshold) {
          reachable++;
        }
      }
      if (minReachable >= reachable) {
        minReachable = reachable;
        result = i;
      }
    }
    return result;
  }
}
