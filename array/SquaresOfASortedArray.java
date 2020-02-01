import java.util.Arrays;

// LC-977
// https://leetcode.com/problems/squares-of-a-sorted-array/

public class SquaresOfASortedArray {

  public int[] sortedSquaresStream(int[] A) {
    return Arrays.stream(A).map(i -> i * i).sorted().toArray();
  }

  public int[] sortedSquares(int[] A) {
    int[] res = new int[A.length];
    int left = 0;
    int right = res.length - 1;

    for (int i = res.length - 1; i >= 0; i--) {
      res[i] = Math.abs(A[right]) < Math.abs(A[left]) ? A[left] * A[left++] : A[right] * A[right--];
    }

    return res;
  }
}
