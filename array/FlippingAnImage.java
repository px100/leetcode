
// LC-832
// https://leetcode.com/problems/flipping-an-image/

public class FlippingAnImage {

  public int[][] flipAndInvertImage(int[][] A) {
    int size = A[0].length;

    for (int[] row : A) {
      for (int i = 0; i < (size + 1) >> 1; i++) {
        int temp = row[i] ^ 1;
        row[i] = row[size - 1 - i] ^ 1;
        row[size - 1 - i] = temp;
      }
    }

    return A;
  }
}
