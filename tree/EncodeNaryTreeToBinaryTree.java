import java.util.ArrayList;
import java.util.List;

// LC-431
// https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/

public class EncodeNaryTreeToBinaryTree {

  private static class Node {

    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int val, List<Node> children) {
      this.val = val;
      this.children = children;
    }
  }

  // Encodes an n-ary tree to a binary tree.
  public TreeNode encode(Node root) {
    if (null == root) {
      return null;
    }

    TreeNode treeNode = new TreeNode(root.val);
    List<Node> children = root.children;
    TreeNode cur = null;
    if (!children.isEmpty()) {
      treeNode.left = encode(children.get(0));
      cur = treeNode.left;
    }

    for (int i = 1; i < children.size(); i++) {
      cur.right = encode(children.get(i));
      cur = cur.right;
    }

    return treeNode;
  }

  // Decodes your binary tree to an n-ary tree.
  public Node decode(TreeNode root) {
    if (null == root) {
      return null;
    }

    Node node = new Node(root.val, new ArrayList<>());
    TreeNode cur = root.left;
    while (null != cur) {
      node.children.add(decode(cur));
      cur = cur.right;
    }

    return node;
  }
}
