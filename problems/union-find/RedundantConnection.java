
// LC-684
// Medium
// https://leetcode.com/problems/redundant-connection/

public class RedundantConnection {

  public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    UnionFind uf = new UnionFind(n);

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      if (uf.find(u) == uf.find(v)) {
        return edge;
      } else {
        uf.union(u, v);
      }
    }
    return null;
  }

  private static class UnionFind {

    int[] parent;

    public UnionFind(int n) {
      parent = new int[n + 1];
      for (int i = 1; i <= n; i++) {
        parent[i] = i;
      }
    }

    public void union(int a, int b) {
      int pa = find(a);
      int pb = find(b);
      parent[pa] = pb;
    }

    public int find(int x) {
      while (parent[x] != x) {
        x = parent[x];
      }
      return x;
    }
  }
}
