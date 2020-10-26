
// LC-785
// https://leetcode.com/problems/is-graph-bipartite

public class IsGraphBipartite {

  public boolean isBipartite(int[][] graph) {
    UnionFind uf = new UnionFind(graph.length);
    for (int i = 0; i < graph.length; i++) {
      for (int adj : graph[i]) {
        if (uf.find(i) == uf.find(adj)) {
          return false;
        }
        uf.union(graph[i][0], adj);
      }
    }
    return true;
  }

  class UnionFind {

    int[] parent;
    int[] size;
    int count;

    public UnionFind(int n) {
      parent = new int[n + 1];
      size = new int[n + 1];
      for (int i = 0; i <= n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
      this.count = n;
    }

    public int find(int i) {
      if (i != parent[i]) {
        parent[i] = find(parent[i]);
      }
      return parent[i];
    }

    public void union(int p, int q) {
      int i = find(p);
      int j = find(q);
      if (size[i] > size[j]) {
        parent[j] = i;
        size[i] += size[j];
      } else {
        parent[i] = j;
        size[j] += size[i];
      }
      this.count--;
    }
  }
}
