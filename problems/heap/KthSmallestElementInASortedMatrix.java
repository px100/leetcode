import java.util.Arrays;
import java.util.stream.Collectors;

// LC-378
// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

public class KthSmallestElementInASortedMatrix {

  public int kthSmallestSlow(int[][] matrix, int k) {
    return Arrays.stream(matrix)
      .flatMapToInt(Arrays::stream)
      .boxed()
      .sorted()
      .collect(Collectors.toList()).get(k - 1);
  }

  public int kthSmallestFast(int[][] matrix, int k) {
    int n = matrix.length;
    int low = matrix[0][0];
    int high = matrix[n - 1][n - 1];

    while (low <= high) {
      int mid = low + (high - low) / 2;
      int count = getLessThan(matrix, n, mid);
      if (count < k) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return low;
  }

  private int getLessThan(int[][] matrix, int n, int val) {
    int i = n - 1;
    int j = 0;
    int result = 0;
    while (i >= 0 && j < n) {
      if (matrix[i][j] > val) {
        i--;
      } else {
        result += i + 1;
        j++;
      }
    }

    return result;
  }
}
