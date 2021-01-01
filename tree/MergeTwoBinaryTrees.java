
// LC-617
// https://leetcode.com/problems/merge-two-binary-trees/

// Given two binary trees and imagine that when you put one of them to cover the other,
// some nodes of the two trees are overlapped while the others are not.
//
// You need to merge them into a new binary tree. The merge rule is that if two nodes overlap,
// then sum node values up as the new value of the merged node.
//
// Otherwise, the NOT null node will be used as the node of new tree.

// Input:
//    Tree 1                     Tree 2
//       1                         2
//      / \                       / \
//     3   2                     1   3
//    /                           \   \
//   5                             4   7
// Output:
//    Merged tree:
//        3
//       / \
//      4   5
//     / \   \
//    5   4   7

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MergeTwoBinaryTrees {

  // TC: O(m), m: minimum number of nodes from the two given trees
  // SC: O(m) worst case; skewed tree
  // SC: O(log(m)), average case
  public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    if (t1 == null) {
      return t2;
    }
    if (t2 == null) {
      return t1;
    }
    t1.val += t2.val;
    t1.left = mergeTrees(t1.left, t2.left);
    t1.right = mergeTrees(t1.right, t2.right);
    return t1;
  }

  public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
    if (t1 == null) {
      return t2;
    }
    if (t2 == null) {
      return t1;
    }
    TreeNode node = new TreeNode(t1.val + t2.val);
    node.left = mergeTrees2(t1.left, t2.left);
    node.right = mergeTrees2(t1.right, t2.right);
    return node;
  }

  // Iterative DFS
  // TC: O(n)
  // SC: O(height)
  public TreeNode mergeTreesDFS(TreeNode t1, TreeNode t2) {
    if (t1 == null) {
      return t2;
    }
    if (t2 == null) {
      return t1;
    }
    Stack<TreeNode[]> stack = new Stack<>();
    stack.push(new TreeNode[]{t1, t2});
    while (!stack.isEmpty()) {
      TreeNode[] cur = stack.pop();
      if (cur[1] == null) {
        continue;
      }
      cur[0].val += cur[1].val;
      if (cur[0].left == null) {
        cur[0].left = cur[1].left;
      } else {
        stack.push(new TreeNode[]{cur[0].left, cur[1].left});
      }
      if (cur[0].right == null) {
        cur[0].right = cur[1].right;
      } else {
        stack.push(new TreeNode[]{cur[0].right, cur[1].right});
      }
    }
    return t1;
  }

  // Iterative BFS
  // TC: O(n)
  // SC: O(n)
  public TreeNode mergeTreesBFS(TreeNode t1, TreeNode t2) {
    if (t1 == null) {
      return t2;
    }
    if (t2 == null) {
      return t1;
    }
    Queue<TreeNode[]> queue = new LinkedList<>();
    queue.offer(new TreeNode[]{t1, t2});
    while (!queue.isEmpty()) {
      TreeNode[] cur = queue.poll();
      if (cur[1] == null) {
        continue;
      }
      cur[0].val += cur[1].val;
      if (cur[0].left == null) {
        cur[0].left = cur[1].left;
      } else {
        queue.offer(new TreeNode[]{cur[0].left, cur[1].left});
      }
      if (cur[0].right == null) {
        cur[0].right = cur[1].right;
      } else {
        queue.offer(new TreeNode[]{cur[0].right, cur[1].right});
      }
    }
    return t1;
  }
}
