
// LC-886
// https://leetcode.com/problems/possible-bipartition

public class PossibleBipartition {

  public boolean possibleBipartition(int N, int[][] dislikes) {
    if (dislikes.length <= 1) {
      return true;
    }

    UnionFind uf = new UnionFind(N << 1);
    for (int[] dislike : dislikes) {
      uf.union(dislike[0], dislike[1] + N);
      uf.union(dislike[1], dislike[0] + N);
    }
    for (int i = 1; i <= N; i++) {
      if (uf.find(i) == uf.find(i + N)) {
        return false;
      }
    }
    return true;
  }

  private static class UnionFind {

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
