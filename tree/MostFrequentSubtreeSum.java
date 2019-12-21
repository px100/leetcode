import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC-508
// https://leetcode.com/problems/most-frequent-subtree-sum/

public class MostFrequentSubtreeSum {

  Map<Integer, Integer> map = new HashMap<>();

  public int[] findFrequentTreeSum(TreeNode root) {
    postorder(root);

    List<Integer> res = new ArrayList<>();
    int max = Integer.MIN_VALUE;

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > max) {
        max = entry.getValue();
        res.clear();
        res.add(entry.getKey());
      } else if (entry.getValue() == max) {
        res.add(entry.getKey());
      }
    }

    return res.stream().mapToInt(i -> i).toArray();
  }

  private int postorder(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = postorder(root.left);
    int right = postorder(root.right);

    int sum = left + right + root.val;
    map.merge(sum, 1, Integer::sum);

    return sum;
  }
}
