import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC-1042
// https://leetcode.com/problems/flower-planting-with-no-adjacent/

public class FlowerPlantingWithNoAdjacent {

  public int[] gardenNoAdj(int N, int[][] paths) {
    Map<Integer, List<Integer>> graph = new HashMap<>(N + 1);
    for (int i = 1; i <= N; i++) {
      graph.put(i, new ArrayList<>());
    }

    for (int[] path : paths) {
      graph.get(path[0]).add(path[1]);
      graph.get(path[1]).add(path[0]);
    }

    int[] color = new int[N];
    for (int i = 1; i < N + 1; i++) {
      boolean[] colorOccupy = new boolean[5];
      for (int neighbor : graph.get(i)) {
        colorOccupy[color[neighbor - 1]] = true;
      }
      for (int j = 1; j < 5; j++) {
        if (!colorOccupy[j]) {
          color[i - 1] = j;
        }
      }
    }

    return color;
  }
}
