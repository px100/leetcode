
// LC-968
// https://leetcode.com/problems/binary-tree-cameras/

public class BinaryTreeCameras {

  // there are 3 cases for any node i.
  // ITSELF
  // 1 The camera is at node i => dp[i][1] = 1 + min(dp[i.left][0],dp[i.left][1]) + min(dp[i.right][0], dp[i.right][1]);
  // CHILDREN
  // 2 The camera is at its children => dp[i][0] = dp[i.left][1] + dp[i.right][0] || dp[i.left][0] + dp[i.right][1] || dp[i.left][1] + dp[i.right][1]
  // NO MONITOR -> needs to be covered by its parent
  // dp[i][1]

  public int minCameraCover(TreeNode root) {
    int[] res = cover(root);

    return res[0] == UNMONITORED ? 1 + res[1] : res[1];
  }

  int LEAF = -1;
  int MONITORED = 1;
  int INSTALLED = 0;
  int UNMONITORED = 2;

  private int[] cover(TreeNode root) {
    if (root == null) {
      return new int[]{LEAF, 0};
    }

    int[] leftState = cover(root.left);
    int[] rightState = cover(root.right);

    if (leftState[0] == UNMONITORED || rightState[0] == UNMONITORED) {
      return new int[]{INSTALLED, 1 + leftState[1] + rightState[1]};
    } else if (leftState[0] == INSTALLED || rightState[0] == INSTALLED) {
      return new int[]{MONITORED, leftState[1] + rightState[1]};
    } else {
      return new int[]{UNMONITORED, leftState[1] + rightState[1]};
    }
  }
}
