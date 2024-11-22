import java.util.LinkedList;
import java.util.Queue;

// LC-1379
// https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/

public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {

  public final TreeNode getTargetCopy(
      final TreeNode original, final TreeNode cloned, final TreeNode target) {

    Queue<TreeNode> originalQueue = new LinkedList<>();
    Queue<TreeNode> clonedQueue = new LinkedList<>();
    originalQueue.add(original);
    clonedQueue.add(cloned);

    while (!originalQueue.isEmpty()) {
      int size = originalQueue.size();
      for (int i = 0; i < size; i++) {
        TreeNode originalNode = originalQueue.poll();
        TreeNode clonedNode = clonedQueue.poll();
        if (originalNode == target) {
          return clonedNode;
        }
        if (originalNode.left != null) {
          originalQueue.add(originalNode.left);
          clonedQueue.add(clonedNode.left);
        }
        if (originalNode.right != null) {
          originalQueue.add(originalNode.right);
          clonedQueue.add(clonedNode.right);
        }
      }
    }
    return null;
  }
}
