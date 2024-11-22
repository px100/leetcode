
// LC-1351
// Easy
// https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/submissions/

import java.util.Arrays;

public class CountNegativeNumbersInASortedMatrix {

  public int countNegatives(int[][] grid) {
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = grid[i].length - 1; j >= 0; j--) {
        if (grid[i][j] >= 0) {
          break;
        }
        count++;
      }
    }
    return count;
  }

  // Binary Search
  // TC: O(rows*log(cols)
  public int countNegativesBS(int[][] grid) {
    // add negative numbers in each row
    return Arrays.stream(grid).mapToInt(this::binarySearch).sum();
  }

  // return negative count in a row
  private int binarySearch(int[] row) {
    int res = 0;
    int low = 0;
    int high = row.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      // if mid is +ve, go to right side
      // if mid is -ve, count negatives in right side and go left side.
      if (row[mid] >= 0) {
        low = mid + 1;
      } else {
        res += high - mid + 1;
        high = mid - 1;
      }
    }
    return res;
  }
}
