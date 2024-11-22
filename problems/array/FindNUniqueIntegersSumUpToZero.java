
// LC-1304
// https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/

public class FindNUniqueIntegersSumUpToZero {

  public int[] sumZero(int n) {
    int[] result = new int[n];
    int mid = n >> 1;
    for (int i = 0; i < (n >> 1); i++) {
      result[i] = -mid;
      result[n - i - 1] = mid--;
    }

    return result;
  }

  public int[] sumZero2(int n) {
    int[] result = new int[n];
    int sum = 0;
    for (int i = 0; i < n; i++) {
      result[i] = i;
      sum += result[i];
      if (i == n - 2) {
        result[i + 1] = -sum;
        break;
      }
    }

    return result;
  }
}
