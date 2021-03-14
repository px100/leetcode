
// LC-1319
// Medium
// https://leetcode.com/problems/number-of-operations-to-make-network-connected/

public class NumberOfOperationsToMakeNetworkConnected {

  public int makeConnected(int n, int[][] connections) {
    if (connections.length < n - 1) {
      return -1;
    }
    UnionFind uf = new UnionFind(n);
    for (int[] edge : connections) {
      uf.union(edge[0], edge[1]);
    }

    return uf.getCount() - 1;
  }

  private class UnionFind {

    private int[] parent;
    private int[] size;
    private int count;

    public UnionFind(int n) {
      this.parent = new int[n + 1];
      this.size = new int[n + 1];
      this.count = n;
      for (int i = 0; i <= n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
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
      if (i == j) {
        return;
      }
      if (size[i] > size[j]) {
        parent[j] = i;
        size[i] += size[j];
      } else {
        parent[i] = j;
        size[j] += size[i];
      }
      count--;
    }

    public int getCount() {
      return count;
    }
  }
}
