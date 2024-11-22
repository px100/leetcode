
// LC-695
// https://leetcode.com/problems/max-area-of-island/

// You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
// You may assume all four edges of the grid are surrounded by water.
//
// The area of an island is the number of cells with a value 1 in the island.
//
// Return the maximum area of an island in grid. If there is no island, return 0.

import java.util.HashMap;
import java.util.Map;

public class MaxAreaOfIsland {

  public int maxAreaOfIsland(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == 1) {
          int id = i * col + j;
          map.put(id, map.size());
        }
      }
    }

    int N = map.size();
    if (N < 1) {
      return 0;
    }

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    UnionFind unionFind = new UnionFind(N);
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == 1) {
          for (int d = 0; d < 4; d++) {
            int ni = i + dx[d];
            int nj = j + dy[d];
            if (ni >= 0 && ni < row && nj >= 0 && nj < col && grid[ni][nj] == 1) {
              int id = i * col + j;
              int nid = ni * col + nj;
              unionFind.union(map.get(id), map.get(nid));
            }
          }
        }
      }
    }

    return unionFind.getMaxSize();
  }

  private class UnionFind {

    private int[] parent;
    private int[] size;
    private int maxSize;

    public UnionFind(int n) {
      this.parent = new int[n];
      this.size = new int[n];
      this.maxSize = 1;
      for (int i = 0; i < n; i++) {
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
      if (size[i] < size[j]) {
        parent[i] = parent[j];
        size[j] += size[i];
        maxSize = Math.max(maxSize, size[j]);
      } else {
        parent[j] = parent[i];
        size[i] += size[j];
        maxSize = Math.max(maxSize, size[i]);
      }
    }

    public int getMaxSize() {
      return maxSize;
    }
  }
}
