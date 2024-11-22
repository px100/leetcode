
// LC-1266
// https://leetcode.com/problems/minimum-time-visiting-all-points/

public class MinimumTimeVisitingAllPoints {

  public int minTimeToVisitAllPoints(int[][] points) {
    int totalTime = 0;
    for (int i = 0; i < points.length - 1; i++) {
      totalTime += Math.max(
          Math.abs(points[i][0] - points[i + 1][0]), Math.abs(points[i][1] - points[i + 1][1]));
    }

    return totalTime;
  }
}
