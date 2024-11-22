import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC-501
// https://leetcode.com/problems/find-mode-in-binary-search-tree/

public class FindModeInBinarySearchTree {

  Map<Integer, Integer> map = new HashMap<>();

  public void inorder(TreeNode root) {
    if (root == null) {
      return;
    }

    inorder(root.left);
    map.merge(root.val, 1, Integer::sum);
    inorder(root.right);
  }

  public int[] findMode(TreeNode root) {
    List<Integer> res = new ArrayList<>();

    inorder(root);

    int max = Integer.MIN_VALUE;
    for (Integer value : map.values()) {
      max = Math.max(max, value);
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() == max) {
        res.add(entry.getKey());
      }
    }

    return res.stream().mapToInt(i -> i).toArray();
  }
}
