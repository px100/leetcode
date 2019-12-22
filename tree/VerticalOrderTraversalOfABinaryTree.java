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
// we use HashMap to record the same position to its Node relationship
// for the same level(horizontal), we use temporiry HashMap to store this relationship.
//   After finish the same level's nodes visit, we sort the values(if same horizontal+same position)
//   and then save it to global hashmap.
// according to min position value and max position value to transfer hashmap to final return.

  public List<List<Integer>> verticalTraversal(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    Queue<TreeNode> queue = new LinkedList<>();
    Queue<Integer> pos = new LinkedList<>();
    queue.offer(root);
    pos.offer(0);
    Map<Integer, List<Integer>> map = new HashMap<>();
    while (!queue.isEmpty()) {
      Map<Integer, List<Integer>> map1 = new HashMap<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode cur = queue.poll();
        int curidx = pos.poll();
        min = Math.min(min, curidx);
        max = Math.max(max, curidx);
        if (map1.containsKey(curidx)) {
          map1.get(curidx).add(cur.val);
        } else {
          List<Integer> temp = new ArrayList<>();
          temp.add(cur.val);
          map1.put(curidx, temp);
        }
        if (cur.left != null) {
          queue.offer(cur.left);
          pos.offer(curidx - 1);
        }
        if (cur.right != null) {
          queue.offer(cur.right);
          pos.offer(curidx + 1);
        }
      }

      map1.forEach((key1, val) -> {
        int key = key1;
        Collections.sort(val);
        if (map.containsKey(key)) {
          map.get(key).addAll(val);
        } else {
          map.put(key, val);
        }
      });
    }

    for (int i = min; i <= max; i++) {
      if (map.containsKey(i)) {
        result.add(map.get(i));
      }
    }

    return result;
  }
}
