import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// LC-502
// https://leetcode.com/problems/ipo/

public class IPO {

  public int findMaximizedCapital(int k, int W, int[] profits, int[] capital) {
    if (profits == null || profits.length == 0) {
      return 0;
    }

    PriorityQueue<Integer> available = new PriorityQueue<>((p1, p2) -> profits[p2] - profits[p1]);
    PriorityQueue<Integer> remaining =
      new PriorityQueue<>(Comparator.comparingInt(p -> capital[p]));

    for (int i = 0; i < profits.length; i++) {
      if (W >= capital[i]) {
        available.offer(i);
      } else {
        remaining.offer(i);
      }
    }

    while (k > 0 && !available.isEmpty()) {
      W += profits[available.poll()];
      while (!remaining.isEmpty() && capital[remaining.peek()] <= W) {
        available.offer(remaining.poll());
      }
      k--;
    }

    return W;
  }

  static class Tuple {

    int profit;
    int capital;

    Tuple(int p, int c) {
      profit = p;
      capital = c;
    }
  }

  public int findMaximizedCapital2(int k, int W, int[] profits, int[] capital) {
    if (profits == null || profits.length == 0) {
      return 0;
    }

    Set<Tuple> set = new HashSet<>();
    PriorityQueue<Tuple> pq = new PriorityQueue<>((p1, p2) -> p2.profit - p1.profit);

    for (int i = 0; i < profits.length; i++) {
      pq.add(new Tuple(profits[i], capital[i]));
    }

    boolean flag = true;
    while (k != 0 && flag) {
      pq.addAll(set);
      set.clear();
      flag = false;
      while (!pq.isEmpty()) {
        Tuple t = pq.poll();
        if (t.capital > W) {
          set.add(t);
        } else {
          k--;
          W += t.profit;
          flag = true;
          break;
        }
      }
    }

    return W;
  }
}
