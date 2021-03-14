
// LC-990
// Medium
// https://leetcode.com/problems/satisfiability-of-equality-equations/

//  Given an array equations of strings that represent relationships between variables,
//  each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".
//  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.
//
//  Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.
//
//  Example 1:
//
//  Input: ["a==b","b!=a"]
//  Output: false
//  Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
//
//  Example 2:
//
//  Input: ["b==a","a==b"]
//  Output: true
//  Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
//
//  Example 3:
//
//  Input: ["a==b","b==c","a==c"]
//  Output: true
//
//  Example 4:
//
//  Input: ["a==b","b!=c","c==a"]
//  Output: false
//
//  Example 5:
//
//  Input: ["c==c","b==d","x!=z"]
//  Output: true
//
//  Note:
//
//  1 <= equations.length <= 500
//  equations[i].length == 4
//  equations[i][0] and equations[i][3] are lowercase letters
//  equations[i][1] is either '=' or '!'
//  equations[i][2] is '='

public class SatisfiabilityOfEqualityEquations {

  public boolean equationsPossible(String[] equations) {
    int n = equations.length;
    UnionFind uf = new UnionFind(26);
    for (String s : equations) {
      if (s.charAt(1) == '=') {
        uf.union(s.charAt(0) - 'a', s.charAt(3) - 'a');
      }
    }
    for (String s : equations) {
      if (s.charAt(1) == '!') {
        if (uf.find(s.charAt(0) - 'a') == uf.find(s.charAt(3) - 'a')) {
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
