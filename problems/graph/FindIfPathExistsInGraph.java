
// LC-1971
// Easy
// https://leetcode.com/problems/find-if-path-exists-in-graph/

// There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
// The edges in the graph are represented as a 2D integer array edges,
// where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi.
// Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
//
// You want to determine if there is a valid path that exists from vertex start to vertex end.
//
// Given edges and the integers n, start, and end, return true if there is a valid path from start to end, or false otherwise.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FindIfPathExistsInGraph {

  // BFS
  public boolean validPathBFS(int n, int[][] edges, int start, int end) {
    Map<Integer, List<Integer>> vertices = new HashMap<>();
    for (int[] edge : edges) {
      int x = edge[0];
      int y = edge[1];
      vertices.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
      vertices.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
    }

    boolean[] visited = new boolean[n];
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    while (queue.size() > 0) {
      int r = queue.poll();
      if (r == end) {
        return true;
      }
      for (int node : vertices.get(r)) {
        if (!visited[node]) {
          visited[node] = true;
          queue.offer(node);
        }
      }
    }
    return false;
  }

  // DSU
  public boolean validPathDSU(int n, int[][] edges, int start, int end) {
    UnionFind uf = new UnionFind(n);
    for (int[] edge : edges) {
      uf.union(edge[0], edge[1]);
    }
    return uf.find(start) == uf.find(end);
  }

  private static class UnionFind {

    int[] parent;

    public UnionFind(int n) {
      parent = new int[n + 1];
      for (int i = 1; i <= n; i++) {
        parent[i] = i;
      }
    }

    public void union(int a, int b) {
      int pa = find(a);
      int pb = find(b);
      parent[pa] = pb;
    }

    public int find(int x) {
      while (parent[x] != x) {
        x = parent[x];
      }
      return x;
    }
  }
}
