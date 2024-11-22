
// LC-803
// Hard
// https://leetcode.com/problems/bricks-falling-when-hit/

//  You are given an m x n binary grid, where each 1 represents a brick and 0 represents an empty space.
//  A brick is stable if:
//  - It is directly connected to the top of the grid, or
//  - At least one other brick in its four adjacent cells is stable.
//
//  You are also given an array hits, which is a sequence of erasures we want to apply.
//  Each time we want to erase the brick at the location hits[i] = (rowi, coli).
//  The brick on that location (if it exists) will disappear.
//  Some other bricks may no longer be stable because of that erasure and will fall.
//  Once a brick falls, it is immediately erased from the grid (i.e., it does not land on other stable bricks).
//
//  Return an array result, where each result[i] is the number of bricks that will fall after the ith erasure is applied.
//
//  Note that an erasure may refer to a location with no brick, and if it does, no bricks drop.
//
//  Example 1:
//
//  Input: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
//  Output: [2]
//  Explanation: Starting with the grid:
//  [[1,0,0,0],
//  [1,1,1,0]]
//  We erase the underlined brick at (1,0), resulting in the grid:
//  [[1,0,0,0],
//  [0,1,1,0]]
//  The two underlined bricks are no longer stable as they are no longer connected to the top nor adjacent to another stable brick, so they will fall. The resulting grid is:
//  [[1,0,0,0],
//  [0,0,0,0]]
//  Hence the result is [2].
//
//  Example 2:
//
//  Input: grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
//  Output: [0,0]
//  Explanation: Starting with the grid:
//  [[1,0,0,0],
//  [1,1,0,0]]
//  We erase the underlined brick at (1,1), resulting in the grid:
//  [[1,0,0,0],
//  [1,0,0,0]]
//  All remaining bricks are still stable, so no bricks fall. The grid remains the same:
//  [[1,0,0,0],
//  [1,0,0,0]]
//  Next, we erase the underlined brick at (1,0), resulting in the grid:
//  [[1,0,0,0],
//  [0,0,0,0]]
//  Once again, all remaining bricks are still stable, so no bricks fall.
//  Hence the result is [0,0].
//
//  Constraints:
//
//  m == grid.length
//  n == grid[i].length
//  1 <= m, n <= 200
//  grid[i][j] is 0 or 1.
//  1 <= hits.length <= 4 * 104
//  hits[i].length == 2
//  0 <= xi <= m - 1
//  0 <= yi <= n - 1
//  All (xi, yi) are unique.

public class BricksFallingWhenHit {

  private final int HIT = -1;
  private final int BRICK = 1;
  private final int VISITED = 2;

  // TC: O(n + h) (n = rows * cols, h = size of hits).
  // SC: O(h)
  public int[] hitBricks(int[][] grid, int[][] hits) {

    // mark erased bricks
    for (int[] hit : hits) {
      if (grid[hit[0]][hit[1]] == BRICK) {
        grid[hit[0]][hit[1]] = HIT;
      }
    }

    // run dfs from top row in order to know which bricks are left after every erase operation.
    // mark these bricks as visited.
    for (int j = 0; j < grid[0].length; j++) {
      if (grid[0][j] == BRICK) {
        dfs(grid, 0, j);
      }
    }

    // Run the hits array in reverse order.
    // We should run a new DFS from here in order to know which cells can be visited.
    // Count the new visited cells except the DFS root cell (because it was erased, it didn't fall)
    int[] result = new int[hits.length];
    for (int k = hits.length - 1; k >= 0; k--) {
      int i = hits[k][0];
      int j = hits[k][1];
      if (grid[i][j] == HIT) {
        grid[i][j] = BRICK;
        if (isConnected(grid, i, j)) {
          result[k] = dfs(grid, i, j) - 1;
        }
      }
    }

    return result;
  }

  private int dfs(int[][] grid, int i, int j) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != BRICK) {
      return 0;
    }
    grid[i][j] = VISITED;
    int sum = 1;
    sum += dfs(grid, i + 1, j);
    sum += dfs(grid, i - 1, j);
    sum += dfs(grid, i, j - 1);
    sum += dfs(grid, i, j + 1);
    return sum;
  }

  private boolean isConnected(int[][] grid, int i, int j) {
    return i - 1 < 0
        || (grid[i - 1][j] == VISITED)
        || (i + 1 < grid.length && grid[i + 1][j] == VISITED)
        || (j - 1 >= 0 && grid[i][j - 1] == VISITED)
        || (j + 1 < grid[0].length && grid[i][j + 1] == VISITED);
  }
}
