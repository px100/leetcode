
// LC-832
// https://leetcode.com/problems/flipping-an-image/

public class FlippingAnImage {

  public int[][] flipAndInvertImage(int[][] A) {
    int size = A[0].length;
    for (int[] row : A) {
      for (int i = 0; (i << 1) < size; i++) {
        int temp = row[i] ^ 1;
        row[i] = row[size - 1 - i] ^ 1;
        row[size - 1 - i] = temp;
      }
    }
    return A;
  }

  public int[][] flipAndInvertImage2(int[][] A) {
    for (int[] row : A) {
      int start = 0;
      int end = row.length - 1;
      while (start <= end) {
        int temp = row[start] ^ 1;
        row[start] = row[end] ^ 1;
        row[end] = temp;
        start++;
        end--;
      }
    }
    return A;
  }
}
