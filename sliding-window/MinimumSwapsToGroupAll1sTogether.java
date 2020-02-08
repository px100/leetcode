import java.util.Arrays;

// LC-1151
// https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/

public class MinimumSwapsToGroupAll1sTogether {

  public static int minSwaps(int[] data) {
    int onesCount = Arrays.stream(data).sum();
    int t = Arrays.stream(data, 0, onesCount).sum();
    int max = t;

    for (int i = onesCount; i < data.length; i++) {
      t += data[i] - data[i - onesCount];
      max = Math.max(max, t);
    }

    return onesCount - max;
  }
}
