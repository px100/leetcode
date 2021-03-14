
// LC-1722
// Medium
// https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/

//  You are given two integer arrays, source and target, both of length n.
//  You are also given an array allowedSwaps where each allowedSwaps[i] = [ai, bi]
//  indicates that you are allowed to swap the elements at index ai and index bi (0-indexed) of array source.
//  Note that you can swap elements at a specific pair of indices multiple times and in any order.
//
//  The Hamming distance of two arrays of the same length, source and target,
//  is the number of positions where the elements are different.
//  Formally, it is the number of indices i for 0 <= i <= n-1 where source[i] != target[i] (0-indexed).
//
//  Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.
//
//  Example 1:
//
//  Input: source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
//  Output: 1
//  Explanation: source can be transformed the following way:
//  - Swap indices 0 and 1: source = [2,1,3,4]
//  - Swap indices 2 and 3: source = [2,1,4,3]
//  The Hamming distance of source and target is 1 as they differ in 1 position: index 3.
//
//  Example 2:
//
//  Input: source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
//  Output: 2
//  Explanation: There are no allowed swaps.
//  The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.
//
//  Example 3:
//
//  Input: source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
//  Output: 0
//
//  Constraints:
//
//  n == source.length == target.length
//  1 <= n <= 105
//  1 <= source[i], target[i] <= 105
//  0 <= allowedSwaps.length <= 105
//  allowedSwaps[i].length == 2
//  0 <= ai, bi <= n - 1
//  ai != bi

import java.util.HashMap;
import java.util.Map;

public class MinimizeHammingDistanceAfterSwapOperations {

  // Find sets of indices which can be rearranged using union-find data structure.
  // Create a frequency map per disjoint set, top level key being the parent index and value being a map of value,count
  // Traverse the target and keep reducing the count by 1.
  //   If the map is not found or the count is already 0 that means there is a difference and increase the diff counter.

  public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
    int n = source.length;
    UnionFind uf = new UnionFind(n);
    for (int[] swaps : allowedSwaps) {
      uf.union(swaps[0], swaps[1]);
    }

    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      int num = source[i];
      int root = uf.find(i);
      map.putIfAbsent(root, new HashMap<>());
      map.get(root).merge(num, 1, Integer::sum);
    }

    int diff = 0;
    for (int i = 0; i < n; i++) {
      int num = target[i];
      int root = uf.find(i);
      if (map.get(root).getOrDefault(num, 0) == 0) {
        diff++;
      } else {
        map.get(root).merge(num, -1, Integer::sum);
      }
    }

    return diff;
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
      if (i == j) {
        return;
      }
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
