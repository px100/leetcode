import java.util.ArrayList;
import java.util.List;

// LC-1557
// https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/

public class MinimumNumberOfVerticesToReachAllNodes {

  // TC - O(E), E = edges.length
  // SC- O(n)
  public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
    List<Integer> result = new ArrayList<>();

    boolean[] inDegree = new boolean[n];
    for (List<Integer> list : edges) {
      inDegree[list.get(1)] = true;
    }

    for (int i = 0; i < n; i++) {
      if (!inDegree[i]) {
        result.add(i);
      }
    }

    return result;
  }
}
