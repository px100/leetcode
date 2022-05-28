public class FindCenterOfStarGraph {

  // https://leetcode.com/problems/find-center-of-star-graph/

  public int findCenter(int[][] edges) {
    int a = edges[0][0];
    int b = edges[0][1];
    int c = edges[1][1];
    int d = edges[1][0];

    if (a == c || a == d) {
      return a;
    }
    return b;
  }
}
