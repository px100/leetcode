import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// LC-449
// https://leetcode.com/problems/serialize-and-deserialize-bst/

public class SerializeAndDeserializeBST {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serialize(root, sb);

    return sb.toString();
  }

  private void serialize(TreeNode root, StringBuilder sb) {
    if (root == null) {
      sb.append('#').append(',');
    } else {
      sb.append(root.val).append(',');
      serialize(root.left, sb);
      serialize(root.right, sb);
    }
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));

    return deserialize(queue);
  }

  private TreeNode deserialize(Queue<String> queue) {
    String s = queue.poll();
    if (s.equals("#")) {
      return null;
    }

    TreeNode root = new TreeNode(Integer.parseInt(s));
    root.left = deserialize(queue);
    root.right = deserialize(queue);

    return root;
  }
}
