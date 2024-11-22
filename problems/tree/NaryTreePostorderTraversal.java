import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// LC-590
// https://leetcode.com/problems/n-ary-tree-postorder-traversal/

public class NaryTreePostorderTraversal {

  private static class Node {

    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }

  List<Integer> result = new ArrayList<>();

  public List<Integer> postorder(Node root) {
    if (root == null) {
      return result;
    }

    for (Node node : root.children) {
      postorder(node);
    }
    result.add(root.val);

    return result;
  }

  public List<Integer> postorder2(Node root) {
    LinkedList<Node> stack = new LinkedList<>();
    LinkedList<Integer> result = new LinkedList<>();

    if (root == null) {
      return result;
    }

    stack.add(root);
    while (!stack.isEmpty()) {
      Node node = stack.pollLast();
      result.add(0, node.val);
      stack.addAll(node.children);
    }

    return result;
  }
}
