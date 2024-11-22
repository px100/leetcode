
// LC-1627
// Hard
// https://leetcode.com/problems/graph-connectivity-with-threshold/

//  We have n cities labeled from 1 to n.
//  Two different cities with labels x and y are directly connected by a bidirectional road
//  if and only if x and y share a common divisor strictly greater than some threshold.
//
//  More formally, cities with labels x and y have a road between them
//  if there exists an integer z such that all of the following are true:
//
//  x % z == 0,
//  y % z == 0, and
//  z > threshold.
//
//  Given the two integers, n and threshold, and an array of queries,
//  you must determine for each queries[i] = [ai, bi] if cities ai and bi are connected directly or indirectly.
//  (i.e. there is some path between them).
//
//  Return an array answer, where answer.length == queries.length and answer[i] is true if for the ith query,
//  there is a path between ai and bi, or answer[i] is false if there is no path.
//
//  Example 1:
//
//  Input: n = 6, threshold = 2, queries = [[1,4],[2,5],[3,6]]
//  Output: [false,false,true]
//  Explanation: The divisors for each number:
//  1:   1
//  2:   1, 2
//  3:   1, 3
//  4:   1, 2, 4
//  5:   1, 5
//  6:   1, 2, 3, 6
//  Using the underlined divisors above the threshold, only cities 3 and 6 share a common divisor, so they are the
//  only ones directly connected. The result of each query:
//  [1,4]   1 is not connected to 4
//  [2,5]   2 is not connected to 5
//  [3,6]   3 is connected to 6 through path 3--6
//
//  Example 2:
//
//  Input: n = 6, threshold = 0, queries = [[4,5],[3,4],[3,2],[2,6],[1,3]]
//  Output: [true,true,true,true,true]
//  Explanation: The divisors for each number are the same as the previous example. However, since the threshold is 0,
//  all divisors can be used. Since all numbers share 1 as a divisor, all cities are connected.
//
//  Example 3:
//
//  Input: n = 5, threshold = 1, queries = [[4,5],[4,5],[3,2],[2,3],[3,4]]
//  Output: [false,false,false,false,false]
//  Explanation: Only cities 2 and 4 share a common divisor 2 which is strictly greater than the threshold 1,
//  so they are the only ones directly connected.
//
//  Please notice that there can be multiple queries for the same pair of nodes [x, y],
//  and that the query [x, y] is equivalent to the query [y, x].
//
//  Constraints:
//
//  2 <= n <= 104
//  0 <= threshold <= n
//  1 <= queries.length <= 105
//  queries[i].length == 2
//  1 <= ai, bi <= cities
//  ai != bi

import java.util.ArrayList;
import java.util.List;

public class GraphConnectivityWithThreshold {

  //  Complexity:
  //
  //  TC: O(N * 𝐻𝑛 * α(N) + q * α(N)) ~ O(N * 𝐻𝑛 + q) ~ O(N * logN + q),
  //  where N is the number of cities, q is the number of queries.
  //
  //  Explain:
  //  Each operation find(), union() of Union-Find costs α(N) < 5 ~ O(1).
  //  Iterate all pair of cities costs: N + N/2 + N/3 + ... + 1 = N * (1/N + 2/N + ... + 1) = N * 𝐻𝑛
  //  Where 𝐻𝑛 ≈ 𝑙𝑛(𝑛)+γ and γ is the famous Euler - Mascheroni constant with γ=0.577215...
  //  So we can consider 𝐻𝑛 as O(logN) when writing complexity.
  //
  //  SC: O(N)
  public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
    UnionFind uf = new UnionFind(n + 1);
    for (int i = threshold + 1; i <= n; i++) {
      for (int j = i * 2; j <= n; j += i) {
        if (i > threshold) {
          uf.union(i, j);
        }
      }
    }

    List<Boolean> result = new ArrayList<>();
    for (int[] query : queries) {
      result.add(uf.find(query[0]) == uf.find(query[1]));
    }

    return result;
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
