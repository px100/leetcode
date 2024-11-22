import java.util.ArrayList;
import java.util.List;

// LC-1367
// https://leetcode.com/problems/linked-list-in-binary-tree/

public class LinkedListInBinaryTree {

  // Time: O(m * min(n, h)), where n is size of binary tree, m is size of linked list
  // Space: O(h), h is height of the binary tree.
  public boolean isSubPathBruteForce(ListNode head, TreeNode root) {
    if (root == null) {
      return false;
    }
    if (dfs(head, root)) {
      return true;
    }
    return isSubPathBruteForce(head, root.left) || isSubPathBruteForce(head, root.right);
  }

  private boolean dfs(ListNode head, TreeNode root) {
    if (head == null) {
      return true;
    }
    if (root == null || head.val != root.val) {
      return false;
    }
    return dfs(head.next, root.left) || dfs(head.next, root.right);
  }

  // KMP
  // Time: O(m+n), where n is size of binary tree, m is size of linked list
  // Space: O(h+m), h is height of the binary tree.
  // Needle in 'haystack' where haystack is the binary tree and needle is the linked list.
  private int[] needle, lps;

  public boolean isSubPath(ListNode head, TreeNode root) {
    needle = linkedListToArray(head);
    lps = buildKmpTable(needle);
    return kmp(root, 0);
  }

  private boolean kmp(TreeNode i, int j) {
    if (j == needle.length) {
      return true;
    }
    if (i == null) {
      return false;
    }
    while (j > 0 && i.val != needle[j]) {
      j = lps[j - 1];
    }
    if (i.val == needle[j]) {
      j++;
    }
    return kmp(i.left, j) || kmp(i.right, j);
  }

  private int[] buildKmpTable(int[] pattern) {
    int n = pattern.length;
    int[] lps = new int[n];
    for (int i = 1, j = 0; i < n; i++) {
      while (j > 0 && pattern[i] != pattern[j]) {
        j = lps[j - 1];
      }
      if (pattern[i] == pattern[j]) {
        lps[i] = ++j;
      }
    }
    return lps;
  }

  private int[] linkedListToArray(ListNode head) {
    List<Integer> list = new ArrayList<>();
    while (head != null) {
      list.add(head.val);
      head = head.next;
    }
    return list.stream().mapToInt(integer -> integer).toArray();
  }
}
