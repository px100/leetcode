import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// LC-1631
// https://leetcode.com/problems/path-with-minimum-effort/

public class PathWithMinimumEffort {

  private static class Node {

    int x;
    int y;
    Integer diff;

    public Node(int x, int y, Integer diff) {
      this.x = x;
      this.y = y;
      this.diff = diff;
    }
  }

  // Dijkstra
  public int minimumEffortPath(int[][] heights) {
    int row = heights.length;
    int col = heights[0].length;
    int[][] diff = new int[row][col];
    for (int[] d : diff) {
      Arrays.fill(d, Integer.MAX_VALUE);
    }
    diff[0][0] = 0;

    boolean[][] visited = new boolean[row][col];
    final int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    Queue<Node> queue = new PriorityQueue<>(Comparator.comparing(a -> a.diff));
    queue.add(new Node(0, 0, diff[0][0]));
    while (!queue.isEmpty()) {
      Node cur = queue.poll();
      visited[cur.x][cur.y] = true;
      if (cur.x == row - 1 && cur.y == col - 1) {
        return cur.diff;
      }
      for (int[] dir : dirs) {
        int nextX = cur.x + dir[0];
        int nextY = cur.y + dir[1];
        if (isValid(nextX, nextY, row, col) && !visited[nextX][nextY]) {
          int curDiff = Math.abs(heights[nextX][nextY] - heights[cur.x][cur.y]);
          int maxDiff = Math.max(curDiff, diff[cur.x][cur.y]);
          if (diff[nextX][nextY] > maxDiff) {
            diff[nextX][nextY] = maxDiff;
            queue.add(new Node(nextX, nextY, maxDiff));
          }
        }
      }
    }
    return diff[row - 1][col - 1];
  }

  private boolean isValid(int x, int y, int row, int col) {
    return x >= 0 && x < row && y >= 0 && y < col;
  }
}
