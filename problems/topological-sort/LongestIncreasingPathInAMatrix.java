
// LC-329
// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

public class LongestIncreasingPathInAMatrix {

  private final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

  public int longestIncreasingPath(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }

    int m = matrix.length;
    int n = matrix[0].length;
    int[][] dp = new int[m][n];
    int maxLength = 1;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        maxLength = Math.max(maxLength, dfs(matrix, i, j, m, n, dp));
      }
    }

    return maxLength;
  }

  private int dfs(int[][] matrix, int x, int y, int m, int n, int[][] dp) {
    if (dp[x][y] != 0) {
      return dp[x][y];
    }

    int result = 1;

    for (int[] dir : dirs) {
      int x1 = x + dir[0];
      int y1 = y + dir[1];
      if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && matrix[x1][y1] > matrix[x][y]) {
        result = Math.max(result, 1 + dfs(matrix, x1, y1, m, n, dp));
      }
    }
    dp[x][y] = result;

    return result;
  }
}
