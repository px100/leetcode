import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// LC-1000
// https://leetcode.com/problems/minimum-cost-to-merge-stones/description/

public class MinimumCostToMergeStones {

  private record State(List<Integer> piles, int cost, int heuristic)
      implements Comparable<State> {
    int totalCost() {
      return this.cost + this.heuristic;
    }

    @Override
    public int compareTo(State other) {
      return Integer.compare(this.totalCost(), other.totalCost());
    }

    @Override
    public boolean equals(Object obj) {
      return obj instanceof State other && this.piles.equals(other.piles);
    }

    @Override
    public int hashCode() {
      return this.piles.hashCode();
    }
  }

  public int mergeStones(int[] stones, int k) {
    int n = stones.length;
    if ((n - 1) % (k - 1) != 0) {
      return -1;
    }
    List<Integer> initialPiles = Arrays.stream(stones)
        .boxed()
        .collect(Collectors.toList());
    int initialHeuristic = calculateHeuristic(initialPiles, k);
    PriorityQueue<State> pq = new PriorityQueue<>();
    pq.offer(new State(initialPiles, 0, initialHeuristic));
    Map<List<Integer>, Integer> visited = new HashMap<>();
    visited.put(initialPiles, 0);
    while (!pq.isEmpty()) {
      State current = pq.poll();
      List<Integer> currentPiles = current.piles;
      int currentCost = current.cost;
      if (currentPiles.size() == 1) {
        return currentCost;
      }
      for (int i = 0; i <= currentPiles.size() - k; i++) {
        List<Integer> newPiles = new ArrayList<>();
        for (int j = 0; j < currentPiles.size(); j++) {
          if (j < i || j >= i + k) {
            newPiles.add(currentPiles.get(j));
          } else if (j == i) {
            int merged = IntStream.range(i, i + k)
                .map(currentPiles::get)
                .sum();
            newPiles.add(merged);
          }
        }
        int mergeCost = IntStream.range(i, i + k)
            .map(currentPiles::get)
            .sum();
        int newCost = currentCost + mergeCost;
        int newHeuristic = calculateHeuristic(newPiles, k);
        if (!visited.containsKey(newPiles) || visited.get(newPiles) > newCost) {
          visited.put(newPiles, newCost);
          pq.offer(new State(newPiles, newCost, newHeuristic));
        }
      }
    }
    return -1;
  }

  private int calculateHeuristic(List<Integer> piles, int k) {
    int m = piles.size();
    if (m < 1) {
      return 0;
    }
    while (m > 1) {
      if ((m - 1) % (k - 1) != 0) {
        return 0;
      }
      m = (m - 1) / (k - 1);
    }
    return 0;
  }
}
