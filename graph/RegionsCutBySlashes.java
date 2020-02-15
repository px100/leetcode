public class RegionsCutBySlashes {

  class UpscaleDFS {

    // Upscale DFS
    public int regionsBySlashes(String[] grid) {
      int n;
      if (grid == null || (n = grid.length) == 0) {
        return 0;
      }

      // transform grid into map
      int[][] map = new int[3 * n][3 * n];

      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          char c = grid[i].charAt(j);
          if (c == '/') {
            map[i * 3][j * 3 + 2] = 1;
            map[i * 3 + 2][j * 3] = 1;
            map[i * 3 + 1][j * 3 + 1] = 1;
          }
          if (c == '\\') {
            map[i * 3][j * 3] = 1;
            map[i * 3 + 1][j * 3 + 1] = 1;
            map[i * 3 + 2][j * 3 + 2] = 1;
          }
        }
      }

      int count = 0;
      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[0].length; j++) {
          if (map[i][j] == 0) {
            count++;
            dfs(map, i, j);
          }
        }
      }

      return count;
    }

    private void dfs(int[][] nums, int x, int y) {
      if (x < 0 || x >= nums.length || y < 0 || y >= nums.length || nums[x][y] != 0) {
        return;
      }

      nums[x][y] = 1;
      dfs(nums, x - 1, y);
      dfs(nums, x + 1, y);
      dfs(nums, x, y - 1);
      dfs(nums, x, y + 1);
    }
  }

  class DSU {

// Observation

// Each square has 4 vertexes, so the N x N grid can be seen as a (N + 1) x (N + 1) vertex network.
//
// In the beginning, the out-ring 4 * N vertexes form a connected component,
// inner (N-1)^2 vertexes form (N-1)^2 connected components independently, we have only one region.
//
// For each square:
//
// If input is ‘ ’, we do nothing;
// If input is ‘/’, we connect lower left vertex with upper right vertex;
// If input is ‘\’, we connect upper left vertex with lower right vertex.
//
// Before we connect two vertexes together, if they belong to the same connected component,
// then the number of regions will increase by 1 after they are connected together.

    public int regionsBySlashes(String[] grid) {
      int count = 1;
      UnionFind uf = new UnionFind(grid.length + 1);
      for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid.length; col++) {
          count += uf.union(row, col, grid[row].charAt(col));
        }
      }

      return count;
    }

    class UnionFind {

      private int width;
      private int[] parent;
      private int[] weight;

      public UnionFind(int n) {
        width = n;
        int size = n * n;
        parent = new int[size];
        weight = new int[size];
        for (int i = 0; i < size; i++) {
          parent[i] = i;
        }
        int gap = size - n;
        for (int i = 0; i < n; i++) {
          parent[i] = 0;
          parent[i + gap] = 0;
        }
        gap = n - 1;
        for (int i = 0; i < size; i += n) {
          parent[i] = 0;
          parent[i + gap] = 0;
        }
        weight[0] = n * 4 - 4;
        for (int i = 1; i < size; i++) {
          weight[i] = 1;
        }
      }

      private int find(int x) {
        while (x != parent[x]) {
          parent[x] = parent[parent[x]];
          x = parent[x];
        }

        return x;
      }

      public int union(int row, int col, char op) {
        if (op == ' ') {
          return 0;
        }

        int rootP;
        int rootQ;
        if (op == '/') {
          rootP = find(row * width + col + 1);
          rootQ = find((row + 1) * width + col);
        } else {
          rootP = find(row * width + col);
          rootQ = find((row + 1) * width + col + 1);
        }

        if (rootP == rootQ) {
          return 1;
        }

        if (weight[rootP] < weight[rootQ]) {
          parent[rootP] = rootQ;
          weight[rootQ] += weight[rootP];
        } else {
          parent[rootQ] = rootP;
          weight[rootP] += weight[rootQ];
        }

        return 0;
      }
    }
  }
}
