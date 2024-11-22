
// LC-1572
// https://leetcode.com/problems/matrix-diagonal-sum/

public class MatrixDiagonalSum {

  public int diagonalSum(int[][] mat) {
    int n = mat.length;
    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += mat[i][i] + mat[i][n - i - 1];
    }
    return n % 2 == 0 ? sum : sum - mat[n / 2][n / 2];
  }
}
