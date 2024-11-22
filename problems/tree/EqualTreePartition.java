import java.util.HashSet;
import java.util.Set;

// LC-663
// https://leetcode.com/problems/equal-tree-partition/

public class EqualTreePartition {

  public boolean checkEqualTree(TreeNode root) {
    if (root == null) {
      return false;
    }

    Set<Integer> set = new HashSet<>();
    int sum = helper(root.left, set) + helper(root.right, set) + root.val;

    return sum % 2 == 0 && set.contains(sum / 2);
  }

  private int helper(TreeNode root, Set<Integer> set) {
    if (root == null) {
      return 0;
    }

    int sum = root.val + helper(root.left, set) + helper(root.right, set);
    set.add(sum);

    return sum;
  }
}
