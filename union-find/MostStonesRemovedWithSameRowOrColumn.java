
// LC-947
// Medium
// https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/

// On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
//
// A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
//
// Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
//
//  Example 1:
//
//  Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
//  Output: 5
//  Explanation: One way to remove 5 stones is as follows:
//  1. Remove stone [2,2] because it shares the same row as [2,1].
//  2. Remove stone [2,1] because it shares the same column as [0,1].
//  3. Remove stone [1,2] because it shares the same row as [1,0].
//  4. Remove stone [1,0] because it shares the same column as [0,0].
//  5. Remove stone [0,1] because it shares the same row as [0,0].
//  Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
//
//  Example 2:
//
//  Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
//  Output: 3
//  Explanation: One way to make 3 moves is as follows:
//  1. Remove stone [2,2] because it shares the same row as [2,0].
//  2. Remove stone [2,0] because it shares the same column as [0,0].
//  3. Remove stone [0,2] because it shares the same row as [0,0].
//  Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
//
//  Example 3:
//
//  Input: stones = [[0,0]]
//  Output: 0
//  Explanation: [0,0] is the only stone on the plane, so you cannot remove it.

//  Constraints:
//
//  1 <= stones.length <= 1000
//  0 <= xi, yi <= 104
//  No two stones are at the same coordinate point.

import java.util.HashMap;
import java.util.Map;

public class MostStonesRemovedWithSameRowOrColumn {

  public int removeStones(int[][] stones) {
    int n = Integer.MIN_VALUE;
    for (int[] stone : stones) {
      n = Math.max(n, Math.max(stone[0], stone[1]));
    }
    n++;
    UnionFind uf = new UnionFind(n + n);
    for (int[] stone : stones) {
      uf.union(stone[0], n + stone[1]);
    }

    Map<Integer, Integer> counts = new HashMap<>();
    for (int[] stone : stones) {
      counts.merge(uf.find(stone[0]), 1, Integer::sum);
    }

    int maxComponentSize = 0;
    for (int value : counts.values()) {
      if (value > 1) {
        maxComponentSize += value - 1;
      }
    }

    return maxComponentSize;
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
