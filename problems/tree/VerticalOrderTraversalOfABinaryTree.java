import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// LC-987
// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

public class VerticalOrderTraversalOfABinaryTree {

// for each node, we record it's position, starting from 0(root), move to left(-1), move to right(+1).
// we use a map to record the same position to its Node relationship
// for the same level(horizontal), we use temporary map to store this relationship.
//   After visiting all nodes at the same level, we sort the values(if same horizontal+same position)
//   and then save it to global hashmap.
// according to min position value and max position value to transfer hashmap to final return.

  public List<List<Integer>> verticalTraversal(TreeNode root) {
    if (root == null) {
      return List.of();
    }
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    Map<Integer, List<Integer>> map = new HashMap<>();
    Queue<TreeNode> queue = new LinkedList<>();
    Queue<Integer> pos = new LinkedList<>();
    queue.offer(root);
    pos.offer(0);
    while (!queue.isEmpty()) {
      Map<Integer, List<Integer>> levelMap = new HashMap<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode cur = queue.poll();
        Integer curIdx = pos.poll();
        min = Math.min(min, curIdx);
        max = Math.max(max, curIdx);
        levelMap.computeIfAbsent(curIdx, k -> new ArrayList<>()).add(cur.val);
        if (cur.left != null) {
          queue.offer(cur.left);
          pos.offer(curIdx - 1);
        }
        if (cur.right != null) {
          queue.offer(cur.right);
          pos.offer(curIdx + 1);
        }
      }
      levelMap.forEach((key, val) -> {
        Collections.sort(val);
        map.computeIfAbsent(key, k -> new ArrayList<>()).addAll(val);
      });
    }
    List<List<Integer>> result = new ArrayList<>();
    for (int i = min; i <= max; i++) {
      if (map.containsKey(i)) {
        result.add(map.get(i));
      }
    }
    return result;
  }
}
