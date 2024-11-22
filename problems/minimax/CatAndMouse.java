import java.util.Arrays;

// LC-913
// https://leetcode.com/problems/cat-and-mouse/
// General Problem : https://arxiv.org/pdf/1511.00984.pdf

public class CatAndMouse {

  private int[][][] dp;

  public int catMouseGame(int[][] graph) {
    int n = graph.length;
    dp = new int[2 * n][n][n];
    for (int[][] i : dp) {
      for (int j = 0; j < dp[0].length; j++) {
        Arrays.fill(i[j], -1);
      }
    }

    return dfs(graph, 0, 1, 2);
  }

  // m is mouse location, c is cat location
  int dfs(int[][] graph, int step, int m, int c) {
    if (step == graph.length * 2) {
      return 0;
    }
    if (m == c) {
      return dp[step][m][c] = 2;
    }
    if (m == 0) {
      return dp[step][m][c] = 1;
    }
    if (dp[step][m][c] != -1) {
      return dp[step][m][c];
    }

    int turn = step % 2;
    if (turn == 0) { // mouse's turn
      boolean win = true; // by default, it's cat win
      for (int i = 0; i < graph[m].length; i++) {
        int next = dfs(graph, step + 1, graph[m][i], c);
        if (next == 1) {
          return dp[step][m][c] = 1;
        } else if (next != 2) {
          win = false;
        }
      }
      if (win) {
        return dp[step][m][c] = 2;
      } else {
        return dp[step][m][c] = 0;
      }
    } else { // cat's turn
      boolean win = true; // by default, it's mouse win
      for (int i = 0; i < graph[c].length; i++) {
        if (graph[c][i] != 0) {
          int next = dfs(graph, step + 1, m, graph[c][i]);
          if (next == 2) {
            return dp[step][m][c] = 2;
          } else if (next != 1) {
            win = false;
          }
        }
      }
      if (win) {
        return dp[step][m][c] = 1;
      } else {
        return dp[step][m][c] = 0;
      }
    }
  }
}
