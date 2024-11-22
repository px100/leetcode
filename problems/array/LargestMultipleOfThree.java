
// LC-1363
// https://leetcode.com/problems/largest-multiple-of-three/

public class LargestMultipleOfThree {

  public String largestMultipleOfThree(int[] digits) {
    int[] freq = new int[10];
    int sum = 0;
    for (int d : digits) {
      freq[d]++;
      sum += d;
    }

    int[] m1 = {1, 4, 7, 2, 5, 8};
    int[] m2 = {2, 5, 8, 1, 4, 7};

    while (sum % 3 != 0) {
      for (int d : (sum % 3 == 1) ? m1 : m2) {
        if (freq[d] > 0) {
          freq[d]--;
          sum -= d;
          break;
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 9; i >= 0; i--) {
      if (freq[i] > 0) {
        while (freq[i] > 0) {
          sb.append(i);
          freq[i]--;
        }
      }
    }

    return sb.length() > 0 && sb.charAt(0) == '0' ? "0" : sb.toString();
  }
}
