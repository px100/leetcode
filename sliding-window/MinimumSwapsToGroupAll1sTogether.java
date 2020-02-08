import java.util.Arrays;

// LC-1151
// https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/

public class MinimumSwapsToGroupAll1sTogether {

  public int minSwaps(int[] data) {
    int onesCount = Arrays.stream(data).sum();
    int t = Arrays.stream(data, 0, onesCount).sum();
    int max = t;

    for (int i = onesCount; i < data.length; i++) {
      t += data[i] - data[i - onesCount];
      max = Math.max(max, t);
    }

    return onesCount - max;
  }

  public int minSwaps2(int[] data) {
    int onesCount = Arrays.stream(data).sum();
    int left = -onesCount;
    int right = 0;

    int t = 0;
    int max = 0;
    while (right < data.length) {
      if (data[right] == 1) {
        t++;
      }
      if (left >= 0 && data[left] == 1) {
        t--;
      }
      max = Math.max(max, t);
      left++;
      right++;
    }

    return onesCount - max;
  }
}
