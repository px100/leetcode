import java.util.HashSet;
import java.util.Set;

// LC-653
// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/

public class TwoSumIVInputIsABST {

  public boolean findTarget(TreeNode root, int k) {
    return find(root, k, new HashSet<>());
  }

  private boolean find(TreeNode root, int k, Set<Integer> set) {
    if (root == null) {
      return false;
    }
    if (set.contains(k - root.val)) {
      return true;
    }

    set.add(root.val);

    return find(root.left, k, set) || find(root.right, k, set);
  }
}
