import java.util.Arrays;
import java.util.Comparator;

// LC-1135
// https://leetcode.com/problems/connecting-cities-with-minimum-cost/

public class ConnectingCitiesWithMinimumCost {

  public int minimumCost(int N, int[][] connections) {
    Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));

    int count = 0;
    UnionFind uf = new UnionFind(N);
    for (int[] connect : connections) {
      if (uf.find(connect[0]) != uf.find(connect[1])) {
        uf.union(connect[0], connect[1]);
        count += connect[2];
      }
      if (uf.count == 1) {
        return count;
      }
    }

    return -1;
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
