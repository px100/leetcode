
// LC-988
// https://leetcode.com/problems/smallest-string-starting-from-leaf/

public class SmallestStringStartingFromLeaf {

  private String ans = "~";

  public String smallestFromLeaf(TreeNode root) {
    return dfs(root, "");
  }

  private String dfs(TreeNode node, String str) {
    if (node == null) {
      return str;
    } // base case, and in case root is null.

    str = (char) (node.val + 'a') + str; // prepend current char to the path string from root.
    if (node.left == null && node.right == null && ans.compareTo(str) > 0) {
      ans = str;
    } // update ans if n is a leaf.
    dfs(node.left, str); // recursion to the left branch.
    dfs(node.right, str); // recursion to the right branch.

    return ans;
  }
}
