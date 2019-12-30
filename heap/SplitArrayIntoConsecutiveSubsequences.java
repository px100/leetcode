import java.util.PriorityQueue;

// LC-659
// https://leetcode.com/problems/split-array-into-consecutive-subsequences/

public class SplitArrayIntoConsecutiveSubsequences {

  static class Interval {

    int start;
    int end;
    int length;

    Interval(int start, int end) {
      this.start = start;
      this.end = end;
      this.length = end - start + 1;
    }
  }

  public boolean isPossible(int[] nums) {
    if (nums == null || nums.length < 3) {
      return false;
    }

    PriorityQueue<Interval> pq = new PriorityQueue<>(
      (a, b) -> (a.end == b.end ? a.length - b.length : a.end - b.end)
    );

    for (int num : nums) {
      while (!pq.isEmpty() && pq.peek().end + 1 < num) {
        if (pq.poll().length < 3) {
          return false;
        }
      }
      if (pq.isEmpty() || pq.peek().end == num) {
        pq.add(new Interval(num, num));
      } else {
        pq.add(new Interval(pq.poll().start, num));
      }
    }

    while (!pq.isEmpty()) {
      if (pq.poll().length < 3) {
        return false;
      }
    }

    return true;
  }
}
