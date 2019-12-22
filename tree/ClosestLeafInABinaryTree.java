import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// LC-742
// https://leetcode.com/problems/closest-leaf-in-a-binary-tree/

public class ClosestLeafInABinaryTree {

  Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
  TreeNode startNode = null;

  public int findClosestLeaf(TreeNode root, int k) {
    buildTreeGraph(null, root, k);

    Queue<TreeNode> queue = new LinkedList<>();
    Set<TreeNode> visited = new HashSet<>();
    queue.offer(startNode);
    visited.add(startNode);
    while (!queue.isEmpty()) {
      TreeNode curr = queue.poll();
      if (curr.left == null && curr.right == null) {
        return curr.val;
      }
      if (graph.containsKey(curr)) {
        for (TreeNode neighbor : graph.get(curr)) {
          if (visited.contains(neighbor)) {
            continue;
          }
          queue.offer(neighbor);
          visited.add(neighbor);
        }
      }
    }

    return -1;
  }

  private void buildTreeGraph(TreeNode parent, TreeNode child, int k) {
    if (parent != null) {
      graph.computeIfAbsent(parent, x -> new ArrayList<>()).add(child);
      graph.computeIfAbsent(child, x -> new ArrayList<>()).add(parent);
    }
    if (child.val == k) {
      startNode = child;
    }
    if (child.left != null) {
      buildTreeGraph(child, child.left, k);
    }
    if (child.right != null) {
      buildTreeGraph(child, child.right, k);
    }
  }
}
