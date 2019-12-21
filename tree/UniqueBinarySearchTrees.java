
//  https://leetcode.com/problems/unique-binary-search-trees/

public class UniqueBinarySearchTrees {

  public int numTrees(int n) {
    long c = 1L;
    for (int i = 0; i < n; i++) {
      c = c * 2 * (2 * i + 1) / (i + 2);
    }

    return (int) c;
  }
}
