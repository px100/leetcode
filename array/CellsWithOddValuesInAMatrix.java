
// LC-1252
// https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/

public class CellsWithOddValuesInAMatrix {

  public int oddCells(int n, int m, int[][] indices) {
    boolean[] rows = new boolean[n];
    boolean[] cols = new boolean[m];

    int nr = 0;
    int nc = 0;

    for (int[] i : indices) {
      rows[i[0]] ^= true;
      cols[i[1]] ^= true;

      nr += rows[i[0]] ? 1 : ~0;
      nc += cols[i[1]] ? 1 : ~0;
    }

    return nr * (m - nc) + nc * (n - nr);
  }
}
