
// LC-200
// Medium
// https://leetcode.com/problems/number-of-islands/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class NumberOfIslands {

  // BFS
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int nr = grid.length;
    int nc = grid[0].length;
    int count = 0;

    for (int r = 0; r < nr; r++) {
      for (int c = 0; c < nc; c++) {
        if (grid[r][c] == '1') {
          count++;
          grid[r][c] = '0';
          Queue<Integer> neighbors = new LinkedList<>();
          neighbors.add(r * nc + c);
          while (!neighbors.isEmpty()) {
            int id = neighbors.remove();
            int row = id / nc;
            int col = id % nc;
            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
              neighbors.add((row - 1) * nc + col);
              grid[row - 1][col] = '0';
            }
            if (row + 1 < nr && grid[row + 1][col] == '1') {
              neighbors.add((row + 1) * nc + col);
              grid[row + 1][col] = '0';
            }
            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
              neighbors.add(row * nc + col - 1);
              grid[row][col - 1] = '0';
            }
            if (col + 1 < nc && grid[row][col + 1] == '1') {
              neighbors.add(row * nc + col + 1);
              grid[row][col + 1] = '0';
            }
          }
        }
      }
    }
    return count;
  }

  // Union Find
  public int numIslandsUnionFind(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    UnionFind uf = new UnionFind(grid);
    int rows = grid.length;
    int cols = grid[0].length;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == '1') {
          for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];
            if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '1') {
              int id1 = i * cols + j;
              int id2 = x * cols + y;
              uf.union(id1, id2);
            }
          }
        }
      }
    }
    return uf.count;
  }

  class UnionFind {

    int[] parent;
    int m;
    int n;
    int count = 0;

    UnionFind(char[][] grid) {
      m = grid.length;
      n = grid[0].length;
      parent = new int[m * n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (grid[i][j] == '1') {
            int id = i * n + j;
            parent[id] = id;
            count++;
          }
        }
      }
    }

    public void union(int node1, int node2) {
      int find1 = find(node1);
      int find2 = find(node2);
      if (find1 != find2) {
        parent[find1] = find2;
        count--;
      }
    }

    public int find(int node) {
      if (parent[node] == node) {
        return node;
      }
      parent[node] = find(parent[node]);
      return parent[node];
    }
  }

  // There is this follow-up: "what if the input data is too big (and sparse)"

  // Lets say the input data is such big that we can not even load it in memory
  // so instead we want to read it line-by-line (for example, from file).
  // Lets say we have a method readR(int r) that returns r row of the matrix;

  // Say currLine is a row we are checking, prevLine - one before it;
  // Now we can read line-by-line and follow these rules:

  // if currLine[i-1] && prevLine[i] are not set, we consider i as a start of the new island;
  // if currLine[i-1] is set but prevLine[i] is not we consider i belongs to island currLine[i-1]
  // if prevLine[i] is set but currLine[i-1] is not we consider i belongs to island prevLine[i]
  // if both currLine[i-1] && prevLine[i] are set, we consider i belongs to currLine[i-1] AND we update prevLine[i] island to be currLine[i-1].

  // For keeping track / updating indices of islands we use Union-Find:
  // keep a map(k,v) where island k is considered connected to island v.

  // When we create a new island, we create k->k record,
  // when updating k, we recursively traverse through all connected islands and update their values to the new one;

  // https://leetcode.com/problems/number-of-islands/discuss/640295/Optimized-by-memory-(follow-up-question-what-if-matrix-is-too-big)
  public int numIslands2(char[][] grid) {
    if (grid.length < 1) {
      return 0;
    }
    int[] firstR = readFirstsR(grid);
    Map<Integer, Integer> sets = new HashMap<>();
    int count = 0;
    // read first row and create first islands:
    // [0 1 0 0 1 0 1] => [0 1 0 0 2 0 3]
    for (int n : firstR) {
      if (n != 0) {
        sets.put(n, n);
        count = n;
      }
    }
    int r = 1;
    while (r < grid.length) {
      char[] line = readR(r, grid);
      int[] secondR = new int[line.length];
      for (int i = 0; i < line.length; i++) {
        if (line[i] == '1') {
          if (i != 0 && secondR[i - 1] != 0) {
            secondR[i] = getRoot(secondR[i - 1], sets);
          } else if (firstR[i] != 0) {
            secondR[i] = getRoot(firstR[i], sets);
          } else {
            count++;
            secondR[i] = count;
            sets.put(count, count);
          }
          if (firstR[i] != secondR[i] && firstR[i] != 0) {
            updateRoots(sets.get(firstR[i]), secondR[i], sets);
          }
        }
      }
      firstR = secondR;
      r++;
    }
    int res = 0;
    for (int k : sets.keySet()) {
      if (sets.get(k) == k) {
        res++;
      }
    }
    return res;
  }

  // union-find "find" part
  private int getRoot(int v, Map<Integer, Integer> sets) {
    int k = v;
    while (k != sets.get(k)) {
      k = sets.get(k);
    }
    return k;
  }

  // connect island k to island newVal
  private void updateRoots(int k, int newVal, Map<Integer, Integer> sets) {
    while (k != newVal) {
      int v = sets.get(k);
      sets.put(k, newVal);
      k = v;
    }
  }

  private char[] readR(int i, char[][] grid) {
    return grid[i];
  }

  // this is ugly and probably can be done better:
  // read first line and connect adjusted '1'
  private int[] readFirstsR(char[][] grid) {
    int[] first = new int[grid[0].length];
    char[] r = readR(0, grid);
    int count = 1;
    int start = 0;

    while (start < first.length) {
      if (r[start] == '1' &&
          (start == 0 || r[start - 1] != '1')) {
        first[start] = count++;
      }
      if (start != 0 && r[start] == '1' && r[start - 1] == '1') {
        first[start] = first[start - 1];
      }
      start++;
    }
    return first;
  }
}
