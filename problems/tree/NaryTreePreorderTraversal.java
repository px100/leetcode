import java.util.ArrayList;
import java.util.List;

// LC-589
// https://leetcode.com/problems/n-ary-tree-preorder-traversal/

public class NaryTreePreorderTraversal {

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

  public List<Integer> preorder(Node root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    result.add(root.val);
    for (Node node : root.children) {
      result.addAll(preorder(node));
    }

    return result;
  }
}
