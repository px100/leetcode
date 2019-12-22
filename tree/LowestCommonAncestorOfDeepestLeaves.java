import java.util.ArrayList;
import java.util.List;

// LC-1123
// https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/

public class LowestCommonAncestorOfDeepestLeaves {

  // First find the deepest leaves of the tree.
  // Then find lca(all deepest leaves).

  // lca(deepest[0], deepest[1], ... deepest[n-1]) =
  // lca(lca(deepest[0], lca(deepest[1], lca(deepest[2], ...., deepest[n-1]))))

  private List<TreeNode> deepest = new ArrayList<>();
  private int maxDepth = 0;

  public TreeNode lcaDeepestLeaves(TreeNode root) {
    if (root == null) {
      return null;
    }

    getDeepestNodes(root, 0);

    // Among all of the deepest nodes find the lca(deepest[0], deepest[1], ... deepest[n-1])
    if (deepest.size() == 1) {
      return deepest.get(0);
    }
    if (deepest.size() == 2) {
      return lca(root, deepest.get(0), deepest.get(1));
    }

    TreeNode lca = lca(root, deepest.get(0), deepest.get(1));

    for (int i = 2; i < deepest.size(); i++) {
      lca = lca(root, lca, deepest.get(i));
    }

    return lca;
  }

  private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    if (root == p) {
      return p;
    }
    if (root == q) {
      return q;
    }

    TreeNode left = lca(root.left, p, q);
    TreeNode right = lca(root.right, p, q);

    if (left != null && right != null) {
      return root;
    }
    if (left != null) {
      return lca(root.left, p, q);
    }

    return lca(root.right, p, q);
  }

  private void getDeepestNodes(TreeNode root, int depth) {
    if (depth > maxDepth) {
      maxDepth = depth; // Update maximum depth
      deepest = new ArrayList<>();
      deepest.add(root);
    } else if (depth == maxDepth) {
      deepest.add(root); // One of the deepest children.
    }

    // Traverse rest of the binary tree
    if (root.left != null) {
      getDeepestNodes(root.left, depth + 1);
    }
    if (root.right != null) {
      getDeepestNodes(root.right, depth + 1);
    }
  }
}
