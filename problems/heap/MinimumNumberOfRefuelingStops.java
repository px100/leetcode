import java.util.Collections;
import java.util.PriorityQueue;

// LC-871
// https://leetcode.com/problems/minimum-number-of-refueling-stops/

public class MinimumNumberOfRefuelingStops {

  // final distance is the total amount of fuel ever added to the tank
  public int minRefuelStops(int target, int startFuel, int[][] stations) {
    int i = 0;
    int refuel = 0;
    int stationsSize = stations.length;

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap

    for (int distance = startFuel; distance < target; distance += pq.poll()) {
      while (i < stationsSize && stations[i][0] <= distance) {
        pq.offer(stations[i++][1]);
      }
      if (pq.isEmpty()) {
        return -1;
      }
      refuel++;
    }

    return refuel;
  }

  public int minRefuelStopsDP(int target, int startFuel, int[][] stations) {
    int size = stations.length;
    long[] dp = new long[size + 1];
    dp[0] = startFuel;
    for (int i = 0; i < size; i++) {
      for (int j = i; j >= 0; j--) {
        if (dp[j] >= stations[i][0]) {
          dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
        }
      }
    }
    for (int i = 0; i <= size; i++) {
      if (dp[i] >= target) {
        return i;
      }
    }

    return -1;
  }
}
