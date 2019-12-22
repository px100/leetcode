import java.util.Arrays;

// LC-685
// https://leetcode.com/problems/redundant-connection-ii/

public class RedundantConnectionII {

  public int[] findRedundantDirectedConnection(int[][] edges) {
    int[] parent = new int[edges.length + 1];
    Arrays.setAll(parent, i -> i);

    int[] can1 = null;
    int[] can2 = null;
    for (int[] e : edges) {
      int p0 = find(parent, e[0]);
      int p1 = find(parent, e[1]);
      if (p1 != e[1]) {
        can1 = e;
      } else if (p0 == e[1]) {
        can2 = e;
      } else {
        parent[e[1]] = e[0];
      }
    }
    if (can1 == null) {
      return can2;
    }
    if (can2 == null) {
      return can1;
    }

    for (int[] e : edges) {
      if (e[1] == can1[1]) {
        return e;
      }
    }

    return new int[2];
  }

  private int find(int[] parent, int i) {
    if (i != parent[i]) {
      parent[i] = find(parent, parent[i]);
    }

    return parent[i];
  }
}
