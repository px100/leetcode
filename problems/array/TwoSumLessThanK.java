import java.util.Arrays;

// LC-1099
// https://leetcode.com/problems/two-sum-less-than-k/

public class TwoSumLessThanK {

  // TC: O(nlogn)
  // SC: O(1)
  public int twoSumLessThanK(int[] A, int K) {
    int res = -1;
    if (A == null || A.length == 0) {
      return res;
    }

    Arrays.sort(A);
    int l = 0;
    int r = A.length - 1;
    while (l < r) {
      int sum = A[l] + A[r];
      if (sum < K) {
        res = Math.max(res, sum);
        l++;
      } else {
        r--;
      }
    }
    return res;
  }
}
