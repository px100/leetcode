import java.util.Arrays;

// LC-1040
// https://leetcode.com/problems/moving-stones-until-consecutive-ii/

public class MovingStonesUntilConsecutiveII {

  public int[] numMovesStonesII(int[] A) {
    Arrays.sort(A);

    int i = 0;
    int n = A.length;
    int low = n;
    int high = Math.max(A[n - 1] - n + 2 - A[1], A[n - 2] - A[0] - n + 2);

    for (int j = 0; j < n; j++) {
      while (A[j] - A[i] >= n) {
        i++;
      }
      low = j - i + 1 == n - 1 && A[j] - A[i] == n - 2
        ? Math.min(low, 2)
        : Math.min(low, n - (j - i + 1));
    }

    return new int[]{low, high};
  }
}
