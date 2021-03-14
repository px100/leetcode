
// LC-547
// Medium
// https://leetcode.com/problems/number-of-provinces/

public class NumberOfProvinces {

  public int findCircleNum(int[][] isConnected) {
    int size = isConnected.length;
    UnionFind uf = new UnionFind(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (isConnected[i][j] == 1 && i != j) {
          uf.union(i, j);
        }
      }
    }
    return uf.getCount();
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
