import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// LC-313
// https://leetcode.com/problems/super-ugly-number/

public class SuperUglyNumber {

  public int nthSuperUglyNumberDP(int n, int[] primes) {
    if (n == 1) {
      return 1;
    }

    int[] indices = new int[primes.length];
    int[] vals = new int[primes.length];
    int[] dp = new int[n];
    int next = 1;
    Arrays.fill(vals, -1);

    for (int i = 0; i < n; i++) {
      dp[i] = next;
      next = Integer.MAX_VALUE;
      for (int j = 0; j < primes.length; j++) {
        if (vals[j] == dp[i]) {
          indices[j]++;
        }
        vals[j] = dp[indices[j]] * primes[j];
        next = Math.min(vals[j], next);
      }
    }

    return dp[n - 1];
  }

  public int nthSuperUglyNumber(int n, int[] primes) {
    if (n == 1) {
      return 1;
    }

    int count = 1;
    Set<Long> visited = new HashSet<>();
    PriorityQueue<Stream> pq = new PriorityQueue<>();
    pq.add(new UglyNumberStream(1, primes));

    while (!pq.isEmpty()) {
      Stream top = pq.poll();
      long peek = top.peek();
      if (top.hasNext()) {
        top.next();
        pq.add(top);
      }
      if (!visited.contains(peek)) {
        visited.add(peek);
        pq.add(new UglyNumberStream(peek, primes));
      } else {
        continue;
      }
      if (n == ++count) {
        return (int) peek;
      }
    }

    return 0;
  }

  private interface Stream {

    long next();

    long peek();

    boolean hasNext();
  }

  private static final class UglyNumberStream implements Stream, Comparable<Stream> {

    int idx;

    long start;

    int[] primes;

    UglyNumberStream(long start, int[] primes) {
      this.start = start;
      this.primes = primes;
    }

    @Override
    public long next() {
      long peek = peek();
      idx++;
      return peek;
    }

    @Override
    public boolean hasNext() {
      return idx < primes.length - 1;
    }

    @Override
    public long peek() {
      return start * primes[idx];
    }

    @Override
    public int compareTo(Stream o) {
      return Long.compare(peek(), o.peek());
    }
  }
}
