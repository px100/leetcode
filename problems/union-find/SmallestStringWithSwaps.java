
// LC-1202
// Medium
// https://leetcode.com/problems/smallest-string-with-swaps/

//  You are given a string s, and an array of pairs of indices in the string pairs
//  where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
//
//  You can swap the characters at any pair of indices in the given pairs any number of times.
//
//  Return the lexicographically smallest string that s can be changed to after using the swaps.
//
//  Example 1:
//
//  Input: s = "dcab", pairs = [[0,3],[1,2]]
//  Output: "bacd"
//  Explanation:
//  Swap s[0] and s[3], s = "bcad"
//  Swap s[1] and s[2], s = "bacd"
//
//  Example 2:
//
//  Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
//  Output: "abcd"
//  Explanation:
//  Swap s[0] and s[3], s = "bcad"
//  Swap s[0] and s[2], s = "acbd"
//  Swap s[1] and s[2], s = "abcd"
//
//  Example 3:
//
//  Input: s = "cba", pairs = [[0,1],[1,2]]
//  Output: "abc"
//  Explanation:
//  Swap s[0] and s[1], s = "bca"
//  Swap s[1] and s[2], s = "bac"
//  Swap s[0] and s[1], s = "abc"
//
//  Constraints:
//
//  1 <= s.length <= 10^5
//  0 <= pairs.length <= 10^5
//  0 <= pairs[i][0], pairs[i][1] < s.length
//  s only contains lower case English letters.

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SmallestStringWithSwaps {

  public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
    int n = s.length();
    UnionFind uf = new UnionFind(n);
    for (List<Integer> pair : pairs) {
      uf.union(pair.get(0), pair.get(1));
    }
    int[] roots = new int[n];
    for (int i = 0; i < n; i++) {
      roots[i] = uf.find(i);
    }
    Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
    for (int i = 0; i < roots.length; i++) {
      map.computeIfAbsent(roots[i], pq -> new PriorityQueue<>()).offer(s.charAt(i));
    }
    StringBuilder sb = new StringBuilder();
    for (int root : roots) {
      sb.append(map.get(root).poll());
    }
    return sb.toString();
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
