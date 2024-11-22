import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

public class SerializeAndDeserializeBinaryTree {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) {
      return "";
    }

    StringBuilder result = new StringBuilder();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node == null) {
        result.append(";").append(" ");
        continue;
      }
      result.append(node.val).append(" ");
      queue.offer(node.left);
      queue.offer(node.right);
    }

    return result.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.equals("")) {
      return null;
    }

    String[] arr = data.split(" ");

    Queue<TreeNode> queue = new LinkedList<>();
    TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
    queue.offer(root);

    for (int i = 1; i < arr.length; i++) {
      TreeNode parent = queue.remove();
      if (!arr[i].equals(";")) {
        TreeNode left = new TreeNode(Integer.parseInt(arr[i]));
        parent.left = left;
        queue.offer(left);
      }
      if (!arr[++i].equals(";")) {
        TreeNode right = new TreeNode(Integer.parseInt(arr[i]));
        parent.right = right;
        queue.offer(right);
      }
    }

    return root;
  }
}
