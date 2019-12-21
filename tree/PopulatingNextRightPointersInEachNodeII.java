import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

public class PopulatingNextRightPointersInEachNodeII {

  private class Node {

    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }
  }

  public Node connect(Node root) {
    if (root == null) {
      return null;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Node node = queue.remove();
        if (i < size - 1) {
          node.next = queue.peek();
        }
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
    }

    return root;
  }

  public Node connectDFS(Node root) {
    helper(root, 0, new HashMap<>());
    return root;
  }

  private void helper(Node root, int depth, Map<Integer, Node> map) {
    if (root == null) {
      return;
    }

    if (map.containsKey(depth)) {
      Node node = map.get(depth);
      node.next = root;
    }
    map.put(depth, root);

    helper(root.left, depth + 1, map);
    helper(root.right, depth + 1, map);
  }
}
