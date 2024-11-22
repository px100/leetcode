import java.util.Arrays;

// LC-787
// https://leetcode.com/problems/cheapest-flights-within-k-stops/

public class CheapestFlightsWithinKStops {

  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    int INF = Integer.MAX_VALUE;
    int[] cost = new int[n];
    Arrays.fill(cost, INF);
    cost[src] = 0;

    int ans = cost[dst];
    for (int i = K; i >= 0; i--) {
      int[] cur = new int[n];
      Arrays.fill(cur, INF);
      for (int[] flight : flights) {
        cur[flight[1]] = Math.min(cur[flight[1]], cost[flight[0]] + flight[2]);
      }
      cost = cur;
      ans = Math.min(ans, cost[dst]);
    }

    return ans == INF ? -1 : ans;
  }
}
