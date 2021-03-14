
// LC-130
// Medium
// https://leetcode.com/problems/surrounded-regions/

//  Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.
//
//  A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//  Example 1:
//
//  Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
//  Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//  Explanation: Surrounded regions should not be on the border,
//  which means that any 'O' on the border of the board are not flipped to 'X'.
//  Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
//  Two cells are connected if they are adjacent cells connected horizontally or vertically.
//
//  Example 2:
//
//  Input: board = [["X"]]
//  Output: [["X"]]
//
//  Constraints:
//
//  m == board.length
//  n == board[i].length
//  1 <= m, n <= 200
//  board[i][j] is 'X' or 'O'.

import java.util.HashSet;
import java.util.Set;

public class SurroundedRegions {

  private final int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
  private int n, m;

  public void solve(char[][] board) {
    n = board.length;
    if (n == 0) {
      return;
    }
    m = board[0].length;
    UnionFind uf = new UnionFind(n * m);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == 'O') {
          for (int[] dir : dirs) {
            int row = dir[0] + i;
            int col = dir[1] + j;
            if (row >= 0 && row < n && col >= 0 && col < m && board[row][col] == 'O') {
              uf.union(getIndex(i, j), getIndex(row, col));
            }
          }
        }
      }
    }

    Set<Integer> remains = new HashSet<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == 'O'
            // is border
            && (i == 0 || i == n - 1 || j == 0 || j == m - 1)) {
          remains.add(uf.find(getIndex(i, j)));
        }
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == 'O' && !remains.contains(uf.find(getIndex(i, j)))) {
          board[i][j] = 'X';
        }
      }
    }
  }

  private int getIndex(int i, int j) {
    return i * m + j;
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
