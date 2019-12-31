
// LC-778
// https://leetcode.com/problems/swim-in-rising-water/

public class SwimInRisingWater {

  private final int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

  // Binary Search
  public int swimInWater(int[][] grid) {
    int n = grid.length;
    int low = 0;
    int high = n * n - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (canReach(grid, mid)) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }

    return low;
  }

  // Check if there is a path from top left to bottom right at time t
  private boolean canReach(int[][] grid, int t) {
    int n = grid.length;
    boolean[][] visited = new boolean[n][n];

    return dfs(grid, 0, 0, visited, t);
  }

  private boolean dfs(int[][] grid, int i, int j, boolean[][] visited, int t) {
    int n = grid.length;
    if (i < 0 || i >= n || j < 0 || j >= n) {
      return false;
    }
    if (visited[i][j] || grid[i][j] > t) {
      return false;
    }
    if (i == n - 1 && j == n - 1) {
      return true;
    }
    visited[i][j] = true;

    for (int[] dir : dirs) {
      if (dfs(grid, i + dir[0], j + dir[1], visited, t)) {
        return true;
      }
    }

    return false;
  }
}
