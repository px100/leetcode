import java.util.Arrays;
import java.util.PriorityQueue;

// LC-857
// https://leetcode.com/problems/minimum-cost-to-hire-k-workers/

public class MinimumCostToHireKWorkers {

  class Worker implements Comparable<Worker> {

    public int quality, wage;

    public Worker(int q, int w) {
      quality = q;
      wage = w;
    }

    public double ratio() {
      return (double) wage / quality;
    }

    public int compareTo(Worker other) {
      return Double.compare(ratio(), other.ratio());
    }
  }

  public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    int N = quality.length;
    Worker[] workers = new Worker[N];
    for (int i = 0; i < N; i++) {
      workers[i] = new Worker(quality[i], wage[i]);
    }
    Arrays.sort(workers);

    double ans = Double.MAX_VALUE;
    int sumq = 0;
    PriorityQueue<Integer> pool = new PriorityQueue<>();
    for (Worker worker : workers) {
      pool.offer(-worker.quality);
      sumq += worker.quality;
      if (pool.size() > K) {
        sumq += pool.poll();
      }
      if (pool.size() == K) {
        ans = Math.min(ans, sumq * worker.ratio());
      }
    }

    return ans;
  }
}
