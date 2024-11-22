import java.util.PriorityQueue;

// LC-1341
// https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/

public class TheKWeakestRowsInAMatrix {

  public int[] kWeakestRows(int[][] mat, int k) {
    int[] result = new int[k];
    PriorityQueue<int[]> pq = new PriorityQueue<>(
      (a, b) -> b[1] != a[1] ? Integer.compare(b[1], a[1]) : Integer.compare(b[0], a[0]));
    int j = 0;
    for (int[] arr : mat) {
      int sum = 0;
      for (int i : arr) {
        sum += i;
      }
      if (pq.size() == k && pq.peek()[1] > sum) {
        pq.poll();
      }
      if (pq.size() < k) {
        pq.offer(new int[]{j, sum});
      }
      j++;
    }
    j = k - 1;
    while (!pq.isEmpty()) {
      result[j--] = pq.poll()[0];
    }

    return result;
  }
}
