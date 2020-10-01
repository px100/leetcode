import java.util.TreeMap;

// LC-732
// https://leetcode.com/problems/my-calendar-iii/

public class MyCalendarIII {

  private TreeMap<Integer, Integer> map;

  public MyCalendarIII() {
    map = new TreeMap<>();
  }

  public int book(int start, int end) {
    map.merge(start, 1, Integer::sum);
    map.merge(end, -1, Integer::sum);
    int count = 0;
    int max = -1;
    for (int value : map.values()) {
      count += value;
      max = Math.max(max, count);
    }

    return max;
  }
}

class MyCalendarThree {

  private static class TreeNode {

    int start;
    int end;
    int count;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int s, int e, int c) {
      start = s;
      end = e;
      count = c;
    }
  }

  TreeNode root;
  int max = 0;

  public MyCalendarThree() {
    root = new TreeNode(0, 1_000_000_000, 0);
  }

  public int book(int start, int end) {
    update(root, start, end);
    return max;
  }

  void update(TreeNode cur, int start, int end) {
    if (cur.start == start && cur.end == end) {
      cur.count++;
      max = Math.max(max, cur.count);
      if (cur.left == null) {
        return;
      }
    }

    if (cur.left == null) {
      if (cur.start == start) {
        cur.left = new TreeNode(start, end, cur.count);
        cur.right = new TreeNode(end, cur.end, cur.count);
        update(cur.left, start, end);
      } else {
        cur.left = new TreeNode(cur.start, start, cur.count);
        cur.right = new TreeNode(start, cur.end, cur.count);
        update(cur.right, start, end);
      }
    } else if (end <= cur.left.end) {
      update(cur.left, start, end);
    } else if (start >= cur.right.start) {
      update(cur.right, start, end);
    } else {
      update(cur.left, start, cur.left.end);
      update(cur.right, cur.right.start, end);
    }
  }
}
