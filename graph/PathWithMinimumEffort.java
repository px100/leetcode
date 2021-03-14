import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

  // Union Find / Kruskal's algorithm
  // We can sort all the edges in increasing order and start adding them one by one into an empty graph
  // until we finally have a path from source vertex to target vertex.
  //
  // This path must be of minimum cost.
  // Adding an edge into the graph (thereby connecting two disconnected graphs) and
  // checking if two vertices have a path are ideal for a Disjoint-set data structure.
  //
  // TC: O(m n log (m n) + m n α(m n)) where α is the inverse of single-valued Ackermann function = O(m n log (m n))
  // SC: O(m n)
  public int minimumEffortPathUnionFind(int[][] heights) {
    int m = heights.length;
    int n = heights[0].length;
    List<int[]> edges = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      if (i > 0) {
        for (int j = 0; j < n; j++) {
          edges.add(
              new int[]{(i - 1) * n + j, i * n + j, Math.abs(heights[i - 1][j] - heights[i][j])});
        }
      }
      for (int j = 0; j < n - 1; j++) {
        edges.add(new int[]{i * n + j, i * n + j + 1, Math.abs(heights[i][j] - heights[i][j + 1])});
      }
    }
    edges.sort(Comparator.comparingInt(a -> a[2]));
    UnionFind uf = new UnionFind(m * n);
    int src = 0;
    int dest = m * n - 1;
    int i = 0;
    while (uf.find(src) != uf.find(dest)) {
      uf.union(edges.get(i)[0], edges.get(i++)[1]);
    }
    return i == 0 ? 0 : edges.get(i - 1)[2];
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
