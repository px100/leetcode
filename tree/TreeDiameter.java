import java.util.ArrayList;
import java.util.List;

// LC-1245
// https://leetcode.com/problems/tree-diameter/

public class TreeDiameter {

  public int treeDiameter(int[][] edges) {
    int n = edges.length + 1;
    List<List<Integer>> tree = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      tree.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      tree.get(edge[0]).add(edge[1]);
      tree.get(edge[1]).add(edge[0]);
    }

    int ret = 0;
    for (int i = 0; i < n; i++) {
      if (tree.get(i).size() == 1) {
        ret = Math.max(ret, dfs(tree, -1, i));
      }
    }

    return ret - 1;
  }

  private int dfs(List<List<Integer>> tree, int pre, int cur) {
    int ret = 0;
    List<Integer> list = tree.get(cur);
    for (int next : list) {
      if (next != pre) {
        ret = Math.max(ret, dfs(tree, cur, next));
      }
    }

    return ret + 1;
  }
}
