import java.util.ArrayList;
import java.util.List;

// LC-1557
// https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/

public class MinimumNumberOfVerticesToReachAllNodes {

  public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
    List<Integer> result = new ArrayList<>();

    boolean[] visited = new boolean[n];
    for (List<Integer> list : edges) {
      visited[list.get(1)] = true;
    }

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        result.add(i);
      }
    }

    return result;
  }
}
