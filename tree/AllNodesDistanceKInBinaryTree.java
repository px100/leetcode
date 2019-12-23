import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// LC-863
// https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

public class AllNodesDistanceKInBinaryTree {

  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Map<TreeNode, TreeNode> map = new HashMap<>();
    findParents(root, null, map);

    Set<TreeNode> set = new HashSet<>();
    Queue<TreeNode> queue = new LinkedList<>();

    int level = 0;

    set.add(target);
    queue.offer(target);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if (level == K) {
          result.add(node.val);
        }
        if (node.left != null) {
          if (!set.contains(node.left)) {
            set.add(node.left);
            queue.offer(node.left);
          }
        }
        if (node.right != null) {
          if (!set.contains(node.right)) {
            set.add(node.right);
            queue.offer(node.right);
          }
        }
        if (map.get(node) != null) {
          if (!set.contains(map.get(node))) {
            set.add(map.get(node));
            queue.offer(map.get(node));
          }
        }
      }
      level++;
    }

    return result;
  }

  private void findParents(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> map) {
    if (root == null) {
      return;
    }

    map.put(root, parent);
    findParents(root.left, root, map);
    findParents(root.right, root, map);
  }
}
