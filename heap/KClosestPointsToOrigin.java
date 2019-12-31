import java.util.Arrays;
import java.util.PriorityQueue;

// LC-973
// https://leetcode.com/problems/k-closest-points-to-origin/

public class KClosestPointsToOrigin {

  int[][] points;

  public int[][] kClosestSort(int[][] points, int K) {
    this.points = points;
    int l = 0;
    int r = points.length - 1;
    while (l < r) {
      int mid = partition(l, r);
      if (mid == K) {
        break;
      }
      if (mid < K) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }

    return Arrays.copyOfRange(this.points, 0, K);
  }

  private int partition(int i, int j) {
    int pivot = distance(points[i]);
    int start = i;
    while (i < j) {
      while (i < j && distance(points[i]) <= pivot) {
        i++;
      }
      while (i <= j && distance(points[j]) >= pivot) {
        j--;
      }
      if (i >= j) {
        break;
      }
      swap(i, j);
    }
    swap(start, j);

    return j;
  }

  private void swap(int i, int j) {
    int[] temp = points[i];
    points[i] = points[j];
    points[j] = temp;
  }

  public int[][] kClosest(int[][] points, int K) {
    PriorityQueue<int[]> pq = new PriorityQueue<>(K,
      (a, b) -> -(a[0] * a[0] + a[1] * a[1] - (b[0] * b[0] + b[1] * b[1])));

    for (int[] point : points) {
      if (pq.size() < K) {
        pq.add(point);
      } else {
        if (distance(pq.peek()) > distance(point)) {
          pq.poll();
          pq.add(point);
        }
      }
    }

    int[][] kClosest = new int[K][2];
    int i = 0;
    while (!pq.isEmpty()) {
      kClosest[i++] = pq.poll();
    }

    return kClosest;
  }

  private int distance(int[] point) {
    return point[0] * point[0] + point[1] * point[1];
  }
}
