import java.util.ArrayList;
import java.util.List;

// LC-699
// https://leetcode.com/problems/falling-squares/

public class FallingSquares {

  private static class Node {

    int x, h;
    Node next;

    Node(int x, int h) {
      this.x = x;
      this.h = h;
    }
  }

  public List<Integer> fallingSquares(int[][] positions) {
    int max = 0;
    List<Integer> res = new ArrayList<>();
    Node head = new Node(0, 0);
    for (int[] p : positions) {
      Node prev = head;
      while (prev.next != null && prev.next.x < p[0]) {
        prev = prev.next;
      }
      int last = prev.h;
      int h = (prev.next == null || prev.next.x > p[0]) ? prev.h + p[1] : p[1];
      Node post = prev.next;
      while (post != null && post.x < p[0] + p[1]) {
        h = Math.max(h, post.h + p[1]);
        last = post.h;
        post = post.next;
      }
      Node cur = new Node(p[0], h);
      prev.next = cur;
      if (post == null || post.x > p[0] + p[1]) {
        cur.next = new Node(p[0] + p[1], last);
        cur = cur.next;
      }
      cur.next = post;
      max = Math.max(max, h);
      res.add(max);
    }

    return res;
  }
}
