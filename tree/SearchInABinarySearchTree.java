
// LC-700
// https://leetcode.com/problems/search-in-a-binary-search-tree/

public class SearchInABinarySearchTree {

  public TreeNode searchBST(TreeNode root, int val) {
    if (root == null) {
      return null;
    }

    TreeNode cur = root;
    while (cur != null) {
      if (val > cur.val) {
        cur = cur.right;
      } else if (val < cur.val) {
        cur = cur.left;
      } else {
        return cur;
      }
    }

    return null;
  }
}
