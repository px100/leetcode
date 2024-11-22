import java.util.Comparator;
import java.util.PriorityQueue;

// LC-407
// https://leetcode.com/problems/trapping-rain-water-ii/

public class TrappingRainWaterII {

  int m, n;
  int result;
  int count = 0;
  int[][] heightMap;
  boolean[][] visited;
  PriorityQueue<int[]> pq;
  int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

  public int trapRainWater(int[][] heightMap) {
    if (heightMap.length == 0 || heightMap[0].length == 0) {
      return 0;
    }

    this.heightMap = heightMap;
    this.m = heightMap.length;
    this.n = heightMap[0].length;
    this.visited = new boolean[m][n];
    this.pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

    for (int i = 0; i < n; i++) {
      visited[0][i] = true;
      pq.offer(new int[]{0, i, heightMap[0][i]});
      count++;
    }
    for (int i = 1; i < m; i++) {
      visited[i][0] = true;
      pq.offer(new int[]{i, 0, heightMap[i][0]});
      count++;
    }
    for (int i = 1; i < n; i++) {
      visited[m - 1][i] = true;
      pq.offer(new int[]{m - 1, i, heightMap[m - 1][i]});
      count++;
    }
    for (int i = 1; i < m - 1; i++) {
      visited[i][n - 1] = true;
      pq.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
      count++;
    }

    while (count < m * n && pq.size() > 0) {
      int[] lowest = pq.poll();
      dfs(lowest[0], lowest[1], heightMap[lowest[0]][lowest[1]]);
    }

    return result;
  }

  private void dfs(int i, int j, int maxHeight) {
    for (int[] d : dirs) {
      int x = i + d[0];
      int y = j + d[1];
      if (x >= 0 && y >= 0 && x < m && y < n && !visited[x][y]) {
        visited[x][y] = true;
        if (heightMap[x][y] >= maxHeight) {
          pq.offer(new int[]{x, y, heightMap[x][y]});
        } else {
          result += maxHeight - heightMap[x][y];
          dfs(x, y, maxHeight);
        }
        count++;
      }
    }
  }
}
