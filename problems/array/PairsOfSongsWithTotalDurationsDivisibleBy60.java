
// LC-1010
// https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

public class PairsOfSongsWithTotalDurationsDivisibleBy60 {

  public int numPairsDivisibleBy60(int[] time) {
    int count = 0;
    int[] map = new int[60];
    for (int t : time) {
      count += map[(60 - t % 60) % 60];
      map[t % 60]++;
    }
    return count;
  }
}
