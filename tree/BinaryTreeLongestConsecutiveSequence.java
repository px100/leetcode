
// https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/

public class BinaryTreeLongestConsecutiveSequence {

  public int longestConsecutive(TreeNode root) {
    return dfs(root, null, 0);
  }

  public int dfs(TreeNode curr, TreeNode parent, int depth) {
    if (curr == null) {
      return 0;
    }

    int currDepth = parent != null && parent.val + 1 == curr.val ? depth + 1 : 1;

    return Math.max(currDepth,
      Math.max(dfs(curr.left, curr, currDepth), dfs(curr.right, curr, currDepth)));
  }
}
