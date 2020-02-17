
// LC-1267
// https://leetcode.com/problems/count-servers-that-communicate/

import java.util.HashMap;
import java.util.Map;

public class CountServersThatCommunicate {

  public int countServers(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int[] rows = new int[grid.length];
    int[] cols = new int[grid[0].length];

    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1) {
          rows[i]++;
          cols[j]++;
          count++;
        }
      }
    }

    for (int i = 0; i < grid.length; i++) {
      if (rows[i] == 1) {
        for (int j = 0; j < grid[i].length; j++) {
          if (grid[i][j] == 1 && cols[j] == 1) {
            count--;
          }
        }
      }
    }

    return count;
  }

  public int countServersMap(int[][] grid) {
    Map<Integer, Integer> rowMap = new HashMap<>();
    Map<Integer, Integer> colMap = new HashMap<>();

    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          rowMap.merge(i, 1, Integer::sum);
          colMap.merge(j, 1, Integer::sum);
        }
      }
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1 && (rowMap.get(i) > 1 || colMap.get(j) > 1)) {
          count++;
        }
      }
    }

    return count;
  }

  class DSU {

    public int countServers(int[][] grid) {
      UnionFind uf = new UnionFind();
      int m = grid.length;
      int n = grid[0].length;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (grid[i][j] == 1) {
            uf.union(i, 252 + j);
          }
        }
      }

      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (grid[i][j] == 1) {
            map.merge(uf.find(i), 1, Integer::sum);
          }
        }
      }

      return map.values().stream().filter(i -> i > 1).mapToInt(i -> i).sum();
    }

    class UnionFind {

      int[] size;
      int[] parent;

      public UnionFind() {
        size = new int[600];
        parent = new int[600];
        for (int i = 0; i < 600; i++) {
          size[i] = 1;
          parent[i] = i;
        }
      }

      public void union(int i, int j) {
        int I = find(i);
        int J = find(j);
        if (I == J) {
          return;
        }
        if (size[I] > size[J]) {
          parent[J] = I;
          size[I] += size[J];
        } else {
          parent[I] = J;
          size[J] += size[I];
        }
      }

      public int find(int x) {
        while (x != parent[x]) {
          x = parent[parent[x]];
        }

        return x;
      }
    }
  }
}
