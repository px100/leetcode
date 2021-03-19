
// LC-952
// Hard
// https://leetcode.com/problems/largest-component-size-by-common-factor/

//  Given a non-empty array of unique positive integers A, consider the following graph
//
//  There are A.length nodes, labelled A[0] to A[A.length - 1];
//  There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
//
//  Return the size of the largest connected component in the graph.
//
//  Example 1:
//
//  Input: [4,6,15,35]
//  Output: 4
//
//  Example 2:
//
//  Input: [20,50,9,63]
//  Output: 2
//
//  Example 3:
//
//  Input: [2,3,6,7,4,12,21,39]
//  Output: 8
//
//  Note:
//
//  1 <= A.length <= 20000
//  1 <= A[i] <= 100000

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LargestComponentSizeByCommonFactor {

  public int largestComponentSize(int[] A) {
    if (A == null || A.length == 0) {
      return 0;
    }

    int maxElement = Arrays.stream(A).max().getAsInt();
    UnionFind uf = new UnionFind(maxElement + 1);

    // put elements having same factors in one subset
    for (int num : A) {
      for (int factor = 2; factor * factor <= num; factor++) {
        if (num % factor == 0) {
          uf.union(num, factor);
          if (num / factor != factor) {
            uf.union(num, num / factor);
          }
        }
      }
    }
    // count connected components
    Map<Integer, Integer> parentFrequencyMap = new HashMap<>();
    int max = Integer.MIN_VALUE;
    for (int num : A) {
      int parent = uf.find(num);
      int count = parentFrequencyMap.merge(parent, 1, Integer::sum);
      max = Math.max(max, count);
    }
    return max;
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
