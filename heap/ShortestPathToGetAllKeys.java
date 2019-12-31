import java.util.ArrayDeque;
import java.util.Queue;

// LC-864
// https://leetcode.com/problems/shortest-path-to-get-all-keys/

public class ShortestPathToGetAllKeys {

  private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public int shortestPathAllKeys(String[] grid) {
    Queue<int[]> queue = new ArrayDeque<>();
    int m = grid.length;
    int n = grid[0].length();
    int k = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        char chr = grid[i].charAt(j);
        if (chr == '@') {
          queue.offer(new int[]{i, j, 0});
        } else if (Character.isLowerCase(chr)) {
          k = Math.max(k, chr - 'a' + 1);
        }
      }
    }

    int level = 0;
    boolean[][][] visited = new boolean[m][n][1 << k];
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] cur = queue.poll();
        if (cur[2] == (1 << k) - 1) {
          return level;
        }
        for (int[] dir : dirs) {
          int x = cur[0] + dir[0];
          int y = cur[1] + dir[1];
          int mask = cur[2];
          if (x < 0 || x >= m || y < 0 || y >= n || grid[x].charAt(y) == '#') {
            continue;
          }
          char chr = grid[x].charAt(y);
          if (Character.isLowerCase(chr)) {
            mask |= 1 << (chr - 'a');
          }
          if (Character.isUpperCase(chr) && (mask & (1 << (chr - 'A'))) == 0) {
            continue;
          }
          if (!visited[x][y][mask]) {
            visited[x][y][mask] = true;
            queue.offer(new int[]{x, y, mask});
          }
        }
      }
      level++;
    }

    return -1;
  }
}
