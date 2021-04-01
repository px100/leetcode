
// LC-1732
// https://leetcode.com/problems/find-the-highest-altitude/

public class FindTheHighestAltitude {

  public int largestAltitude(int[] gain) {
    int max = 0;
    int altitude = 0;
    for (int g : gain) {
      altitude += g;
      max = Math.max(max, altitude);
    }
    return max;
  }
}
