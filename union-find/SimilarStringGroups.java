
// LC-839
// Hard
// https://leetcode.com/problems/similar-string-groups/

//  Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.
//  Also two strings X and Y are similar if they are equal.
//
//  For example, "tars" and "rats" are similar (swapping at positions 0 and 2),
//  and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
//
//  Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.
//  Notice that "tars" and "arts" are in the same group even though they are not similar.
//  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
//
//  We are given a list strs of strings where every string in strs is an anagram of every other string in strs.
//  How many groups are there?
//
//  Example 1:
//
//  Input: strs = ["tars","rats","arts","star"]
//  Output: 2
//
//  Example 2:
//
//  Input: strs = ["omv","ovm"]
//  Output: 1
//
//  Constraints:
//
//  1 <= strs.length <= 300
//  1 <= strs[i].length <= 300
//  strs[i] consists of lowercase letters only.
//  All words in strs have the same length and are anagrams of each other.

import java.util.HashSet;
import java.util.Set;

public class SimilarStringGroups {

  // union-find
  // TC
  public int numSimilarGroups(String[] strs) {
    if (strs == null || strs.length == 0) {
      return 0;
    }

    UnionFind uf = new UnionFind(strs.length);
    for (int i = 0; i < strs.length; i++) {
      for (int j = i + 1; j < strs.length; j++) {
        if (areSimilar(strs[i], strs[j])) {
          uf.union(i, j);
        }
      }
    }
    return uf.getCount();
  }

  // dfs
  // TC: O(N*W)
  // SC: O(2*N)
  public int numSimilarGroupsDfs(String[] strs) {
    if (strs == null || strs.length == 0) {
      return 0;
    }

    int count = 0;
    Set<String> visited = new HashSet<>();
    for (String str : strs) {
      if (!visited.contains(str)) {
        dfs(str, strs, visited);
        count++;
      }
    }
    return count;
  }

  private void dfs(String cur, String[] strs, Set<String> visited) {
    if (visited.contains(cur)) {
      return;
    }
    visited.add(cur);
    for (String str : strs) {
      if (areSimilar(cur, str)) {
        dfs(str, strs, visited);
      }
    }
  }

  private boolean areSimilar(String s1, String s2) {
    int count = 0;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        count++;
        if (count > 2) {
          return false;
        }
      }
    }
    return true;
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
