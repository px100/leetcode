
// LC-685
// https://leetcode.com/problems/redundant-connection-ii/

import java.util.Arrays;

public class RedundantConnectionII {

  // We still use unionFind to solve this question, but it has two kind of cases
  // Case 1: No duplicate parents, return the first edge that creates the loop --> Same as 684
  // Case 2: A node has two parents {u1, u2}
  //  2.1: return the second edge that creates duplicate parents (no loop). Example:[[1,2], [1,3], [2,3]]
  //          1
  //         / \
  //        v   v
  //        2-->3
  //       Node 3 has two parents: 1 and 2.
  //       Remove any one of the edge satisfy the question.
  //       But we need to remove the one that occurs the last.
  //  2.2: return edge {u1, v} or edge {u2, v} that creates the loop. Example:[[2,1],[3,1],[4,2],[1,4]]
  //          2--> 1 <-- 3
  //          /\   |
  //           \   v
  //            \- 4
  //       Node 1 has two parents.
  //       We have to remove either {2,1} or {3,1}. {2,1} is the one that creates the loop. So we remove {2,1}
  //
  // So our algorithm uses two loops: First loop to detect if there is any duplicate parents.
  // 2nd loop to detect if there is any loop in the graph.

  public int[] findRedundantDirectedConnection(int[][] edges) {
    int size = edges.length;
    int[] inDegrees = new int[size + 1];
    int has2InDegrees = -1;
    for (int[] e : edges) {
      inDegrees[e[1]]++;
      if (inDegrees[e[1]] == 2) {
        has2InDegrees = e[1];
        break;
      }
    }
    if (has2InDegrees == -1) {
      return detectCycle(edges, size, null);
    }
    for (int i = size - 1; i >= 0; --i) {
      if (edges[i][1] == has2InDegrees) {
        if (detectCycle(edges, size, edges[i]) == null) {
          return edges[i];
        }
      }
    }
    return new int[2];
  }

  private int[] detectCycle(int[][] edges, int size, int[] skipEdge) {
    UnionFind uf = new UnionFind(size);
    for (int[] edge : edges) {
      if (Arrays.equals(edge, skipEdge)) {
        continue;
      }
      if (uf.isConnect(edge[0], edge[1])) {
        return edge;
      }
      uf.union(edge[0], edge[1]);
    }
    return null;
  }

  private static class UnionFind {

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

    public boolean isConnect(int u, int v) {
      return find(u) == find(v);
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
