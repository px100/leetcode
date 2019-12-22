
// LC-684
// https://leetcode.com/problems/redundant-connection/

public class RedundantConnection {

  public int[] findRedundantConnection(int[][] edges) {
    int N = edges.length;
    int[] p = new int[N + 1];

    for (int[] e : edges) {
      int x = find(p, e[0]);
      int y = find(p, e[1]);
      if (x == y) {
        return e;
      }
      // Union.
      // connect the 2 ends of the edge into one set, which share the same root parent
      p[y] = x;
    }

    return new int[2];
  }

  // Path compression
  // Form a flat tree and reduce complexity to O(N) for finding root of N nodes
  private int find(int[] p, int t) {
    if (p[t] == 0) {
      return t;
    }

    p[t] = find(p, p[t]); // Update t's parent to root parent while trying to find the root parent

    return p[t];
  }
}
