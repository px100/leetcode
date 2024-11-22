import java.util.ArrayList;
import java.util.List;

// LC-109
// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/¬

public class ConvertSortedListToBinarySearchTree {

  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
      return null;
    }

    List<Integer> list = new ArrayList<>();
    while (head != null) {
      list.add(head.val);
      head = head.next;
    }

    return helper(0, list.size() - 1, list);
  }

  private TreeNode helper(int first, int last, List<Integer> list) {
    if (first > last) {
      return null;
    }

    int mid = first + (last - first) / 2;

    TreeNode root = new TreeNode(list.get(mid));
    root.left = helper(first, mid - 1, list);
    root.right = helper(mid + 1, last, list);

    return root;
  }
}
