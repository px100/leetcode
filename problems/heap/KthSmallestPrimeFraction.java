import java.util.Comparator;
import java.util.PriorityQueue;

// LC-786
// https://leetcode.com/problems/k-th-smallest-prime-fraction/

public class KthSmallestPrimeFraction {

  public int[] kthSmallestPrimeFraction(int[] A, int K) {
    PriorityQueue<int[]> pq =
      new PriorityQueue<>(Comparator.comparingDouble(a -> A[a[0]] / (double) A[a[1]]));

    for (int i = 0; i < A.length; i++) {
      pq.offer(new int[]{i, A.length - 1});
    }

    while (--K > 0) {
      int[] t = pq.poll();
      pq.offer(new int[]{t[0], t[1] - 1});
    }

    return new int[]{A[pq.peek()[0]], A[pq.peek()[1]]};
  }

  public int[] kthSmallestPrimeFractionFast(int[] A, int K) {
    double low = 0;
    double high = 1;
    int p = 0;
    int q = 1;
    int n = A.length;
    for (int count = 0; true; count = p = 0) {
      double m = (low + high) / 2;
      for (int i = 0, j = n - 1; i < n; i++) {
        while (j >= 0 && A[i] > m * A[n - 1 - j]) {
          j--;
        }
        count += (j + 1);
        if (j >= 0 && p * A[n - 1 - j] < q * A[i]) {
          p = A[i];
          q = A[n - 1 - j];
        }
      }
      if (count < K) {
        low = m;
      } else if (count > K) {
        high = m;
      } else {
        return new int[]{p, q};
      }
    }
  }
}
