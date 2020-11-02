import java.util.LinkedList;
import java.util.Queue;

// LC-1162
// https://leetcode.com/problems/as-far-from-land-as-possible/

public class AsFarFromLandAsPossible {

  // TC: O(N * M)
  // SC: O(N * M)
  public int maxDistanceBFS(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    if (m == 0) {
      return -1;
    }

    Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) {
          queue.offer(new int[]{i, j, 0});
        }
      }
    }

    if (queue.size() == n * m) {
      return -1;
    }

    final int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    int distance = -1;
    while (!queue.isEmpty()) {
      for (int i = 0; i < queue.size(); i++) {
        int[] cur = queue.poll();
        distance = cur[2];
        for (int[] dir : dirs) {
          int newX = cur[0] + dir[0];
          int newY = cur[1] + dir[1];
          if (newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == 0) {
            grid[newX][newY] = 1 + distance;
            queue.offer(new int[]{newX, newY, 1 + distance});
          }
        }
      }
    }

    return distance;
  }

  public int maxDistanceDP(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) {
          continue;
        }
        grid[i][j] = 201;
        if (i > 0) {
          grid[i][j] = Math.min(grid[i][j], grid[i - 1][j] + 1);
        }
        if (j > 0) {
          grid[i][j] = Math.min(grid[i][j], grid[i][j - 1] + 1);
        }
      }
    }

    int result = 0;
    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--) {
        if (grid[i][j] == 1) {
          continue;
        }
        if (i < n - 1) {
          grid[i][j] = Math.min(grid[i][j], grid[i + 1][j] + 1);
        }
        if (j < m - 1) {
          grid[i][j] = Math.min(grid[i][j], grid[i][j + 1] + 1);
        }
        result = Math.max(result, grid[i][j]);
      }
    }

    return result == 201 ? -1 : result - 1;
  }
}
