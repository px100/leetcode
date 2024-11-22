import java.util.Arrays;

// LC-1100
// https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/

public class FindKLengthSubstringsWithNoRepeatedCharacters {

  public int numKLenSubstrNoRepeats(String S, int K) {
    if (S.isEmpty() || S.length() < K) {
      return 0;
    }

    int[] chars = new int[256];
    Arrays.fill(chars, Integer.MIN_VALUE);

    int res = 0;
    int left = 0;
    int right = 0;
    while (right < S.length()) {
      if (chars[S.charAt(right)] >= left) {
        left = chars[S.charAt(right)] + 1;
      }
      chars[S.charAt(right)] = right;
      if (right - left + 1 == K) {
        res++;
        left++;
      }
      right++;
    }

    return res;
  }
}
