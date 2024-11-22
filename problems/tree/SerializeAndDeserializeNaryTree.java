import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// LC-428
// https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree

public class SerializeAndDeserializeNaryTree {

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

  // Encodes a tree to a single string.
  public String serialize(Node root) {
    if (root == null) {
      return null;
    }

    List<String> list = new LinkedList<>();
    serializeHelper(root, list);

    return String.join(",", list);
  }

  private void serializeHelper(Node root, List<String> list) {
    if (root == null) {
      return;
    }

    list.add("" + root.val);
    list.add("" + root.children.size());
    for (Node child : root.children) {
      serializeHelper(child, list);
    }
  }

  // Decodes your encoded data to tree.
  public Node deserialize(String data) {
    if (data.isBlank()) {
      return null;
    }

    Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));

    return deserializeHelper(queue);
  }

  private Node deserializeHelper(Queue<String> queue) {
    Node root = new Node();
    root.val = Integer.parseInt(queue.poll());
    int size = Integer.parseInt(queue.poll());
    root.children = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      root.children.add(deserializeHelper(queue));
    }

    return root;
  }
}
