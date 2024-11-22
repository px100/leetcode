import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// LC-429
// https://leetcode.com/problems/n-ary-tree-level-order-traversal/

public class NaryTreeLevelOrderTraversal {

  private static class Node {

    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }

  public List<List<Integer>> levelOrder(Node root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> level = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        Node current = queue.poll();
        level.add(current.val);
        for (Node child : current.children) {
          if (child != null) {
            queue.offer(child);
          }
        }
      }
      result.add(level);
    }

    return result;
  }
}
