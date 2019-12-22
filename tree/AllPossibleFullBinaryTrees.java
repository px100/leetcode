import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// LC-894
// https://leetcode.com/problems/all-possible-full-binary-trees/

public class AllPossibleFullBinaryTrees {

  Map<Integer, List<TreeNode>> map = new HashMap<>();

  public List<TreeNode> allPossibleFBT(int N) {
    if (map.containsKey(N)) {
      return map.get(N);
    }

    List<TreeNode> result = new LinkedList<>();
    if (N == 1) {
      result.add(new TreeNode(0));
    } else if (N % 2 == 1) {
      for (int x = 0; x < N; x++) {
        int y = N - 1 - x;
        for (TreeNode left : allPossibleFBT(x)) {
          for (TreeNode right : allPossibleFBT(y)) {
            TreeNode node = new TreeNode(0);
            node.left = left;
            node.right = right;
            result.add(node);
          }
        }
      }
    }
    map.put(N, result);

    return map.get(N);
  }
}
