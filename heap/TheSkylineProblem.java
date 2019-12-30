import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// LC-218
// https://leetcode.com/problems/the-skyline-problem/

public class TheSkylineProblem {

  static class Point implements Comparable {

    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int compareTo(Object p) {
      return ((Point) p).y - this.y;
    }
  }

  public List<List<Integer>> getSkyline(int[][] buildings) {
    List<List<Integer>> result = new ArrayList<>();
    if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
      return result;
    }

    int buildingsSize = buildings.length;

    // max heap ordered by the height of the building
    PriorityQueue<Point> queue = new PriorityQueue<>();
    int[] points = new int[buildingsSize << 1];
    for (int i = 0; i < buildingsSize; i++) {
      points[2 * i] = buildings[i][0];
      points[2 * i + 1] = buildings[i][1];
    }

    // only examine interested points where a building starts or ends
    Arrays.sort(points);
    int index = 0;
    int prevH = 0;
    int prevP = -1;
    for (int p : points) {
      // remove candidates whose x coordinate is smaller than current pos
      while (!queue.isEmpty() && queue.peek().x <= p) {
        queue.poll();
      }
      while (index < buildingsSize && p == buildings[index][0]) {
        // encounter a new building
        // add the height and the end of the building to the queue
        queue.add(new Point(buildings[index][1], buildings[index][2]));
        ++index;
      }
      if (prevP == p) {
        continue; // skip duplicates ending positions
      } else {
        prevP = p;
      }
      if (queue.isEmpty()) {
        result.add(Arrays.asList(p, 0));
        prevH = 0;
      } else {
        int h = queue.peek().y;
        if (prevH != h) { // only care about situations where the max height changes
          result.add(Arrays.asList(p, h));
          prevH = h;
        }
      }
    }

    return result;
  }
}
