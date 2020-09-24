import java.util.TreeSet;

// LC-855
// https://leetcode.com/problems/exam-room/

public class ExamRoom {

  class Interval implements Comparable<Interval> {

    public int L, R, dist, index;

    Interval(int L, int R) {
      this.L = L;
      this.R = R;
      if (L == 0) {
        dist = R - L;
        index = L;
      } else if (R == N - 1) {
        dist = R - L;
        index = R;
      } else {
        dist = (R - L) >> 1;
        index = L + dist;
      }
    }

    @Override
    public int compareTo(Interval i) {
      return dist == i.dist ? i.index - index : dist - i.dist;
    }
  }

  private int N;
  private TreeSet<Integer> seats = new TreeSet<>();
  private TreeSet<Interval> intervals = new TreeSet<>();

  public ExamRoom(int N) {
    this.N = N;
    intervals.add(new Interval(0, N - 1));
  }

  public int seat() {
    Interval max = intervals.pollLast();
    int index = max.index;
    seats.add(index);
    if (max.L < index) {
      intervals.add(new Interval(max.L, index - 1));
    }
    if (max.R > index) {
      intervals.add(new Interval(index + 1, max.R));
    }
    return index;
  }

  public void leave(int p) {
    seats.remove(p);
    Integer left = seats.floor(p);
    Integer right = seats.ceiling(p);
    int L = left == null ? 0 : left + 1;
    int R = right == null ? N - 1 : right - 1;
    if (L < p) {
      intervals.remove(new Interval(L, p - 1));
    }
    if (R > p) {
      intervals.remove(new Interval(p + 1, R));
    }
    intervals.add(new Interval(L, R));
  }
}
