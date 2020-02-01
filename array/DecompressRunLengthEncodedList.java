
// LC-1313
// https://leetcode.com/problems/decompress-run-length-encoded-list/

import java.util.Arrays;

public class DecompressRunLengthEncodedList {

  public int[] decompressRLElist(int[] nums) {
    int maxSize = 0;
    for (int i = 0; i < nums.length; i += 2) {
      maxSize += nums[i];
    }
    int[] result = new int[maxSize];
    int currentIndex = 0;
    for (int i = 0; i < nums.length; i += 2) {
      int freq = nums[i];
      int value = nums[i + 1];
      for (int j = 0; j < freq; j++) {
        result[currentIndex++] = value;
      }
    }

    return result;
  }

  public int[] decompressRLElist2(int[] nums) {
    int maxSize = 0;
    for (int i = 0; i < nums.length; i += 2) {
      maxSize += nums[i];
    }
    int[] result = new int[maxSize];
    int currentIndex = 0;
    for (int j = 1; j < nums.length; j += 2) {
      Arrays.fill(result, currentIndex, currentIndex + nums[j - 1], nums[j]);
      currentIndex += nums[j - 1];
    }

    return result;
  }
}
