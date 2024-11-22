
// LC-1584
// https://leetcode.com/problems/min-cost-to-connect-all-points/

//  You are given an array points representing integer coordinates of some points on a 2D-plane,
//  where points[i] = [xi, yi].
//
//  The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them:
//  |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
//
//  Return the minimum cost to make all points connected.
//  All points are connected if there is exactly one simple path between any two points.
//
//  Example 1:
//
//  Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//  Output: 20
//  Explanation:
//
//  We can connect the points as shown above to get the minimum cost of 20.
//  Notice that there is a unique path between every pair of points.

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinCostToConnectAllPoints {

  private class Edge {

    int from;
    int to;
    int cost;

    Edge(int i, int j, int cost) {
      from = i;
      to = j;
      this.cost = cost;
    }
  }

  // Kruskal with Union Find
  public int minCostConnectPoints(int[][] points) {
    if (points == null || points.length == 0) {
      return 0;
    }
    int n = points.length;
    UnionFind uf = new UnionFind(n);
    Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        pq.offer(new Edge(i, j, dist(points, i, j)));
      }
    }

    int count = 0;
    int minCost = 0;
    while (!pq.isEmpty() && count < n - 1) {
      Edge edge = pq.poll();
      if (uf.union(edge.from, edge.to)) {
        minCost += edge.cost;
        count++;
      }
    }
    return minCost;
  }

  private int dist(int[][] points, int i, int j) {
    return Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
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

    public boolean union(int p, int q) {
      int i = find(p);
      int j = find(q);
      if (i == j) {
        return false;
      }
      if (size[i] > size[j]) {
        parent[j] = i;
        size[i] += size[j];
      } else {
        parent[i] = j;
        size[j] += size[i];
      }
      count--;
      return true;
    }

    public int getCount() {
      return count;
    }
  }
}
