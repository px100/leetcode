import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// LC-1305
// https://leetcode.com/problems/all-elements-in-two-binary-search-trees/

public class AllElementsInTwoBinarySearchTrees {

  // Time: O(n)
  // Space: O(h)
  public class TreeIterator {

    TreeNode treeNode;
    Deque<TreeNode> stack = new ArrayDeque<>();

    public TreeIterator(TreeNode root) {
      this.treeNode = root;
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
    }

    public boolean hasNext() {
      return !stack.isEmpty();
    }

    // returns next inorder node
    public int next() {
      TreeNode node = stack.pop();
      if (node.right != null) {
        stack.push(node.right);
        TreeNode current = node.right.left;
        while (current != null) {
          stack.push(current);
          current = current.left;
        }
      }
      return node.val;
    }
  }

  private void addTail(List<Integer> result, TreeIterator itr1, TreeIterator itr2) {
    if (itr1.hasNext() && !itr2.hasNext()) {
      while (itr1.hasNext()) {
        result.add(itr1.next());
      }
      return;
    }
    if (!itr1.hasNext() && itr2.hasNext()) {
      while (itr2.hasNext()) {
        result.add(itr2.next());
      }
    }
  }

  public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
    TreeIterator itr1 = new TreeIterator(root1);
    TreeIterator itr2 = new TreeIterator(root2);
    List<Integer> result = new ArrayList<>();

    if (!itr1.hasNext() && !itr2.hasNext()) {
      return result;
    }

    Integer value1 = itr1.hasNext() ? itr1.next() : null;
    Integer value2 = itr2.hasNext() ? itr2.next() : null;

    while (value1 != null && value2 != null) {
      if (value1 < value2) {
        result.add(value1);
        value1 = itr1.hasNext() ? itr1.next() : null;
      } else {
        result.add(value2);
        value2 = itr2.hasNext() ? itr2.next() : null;
      }
    }
    if (value1 != null) {
      result.add(value1);
    }
    if (value2 != null) {
      result.add(value2);
    }
    addTail(result, itr1, itr2);

    return result;
  }
}
