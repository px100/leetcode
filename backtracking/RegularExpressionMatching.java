import java.util.LinkedList;
import java.util.Queue;

// LC-10
// https://leetcode.com/problems/regular-expression-matching/

public class RegularExpressionMatching {

  // Time: O(T*P)
  // Space: O(T*P)
  public boolean isMatch(String s, String p) {
    int textSize = s.length();
    int patternSize = p.length();
    boolean[][] dp = new boolean[textSize + 1][patternSize + 1];
    dp[textSize][patternSize] = true;

    for (int i = textSize; i >= 0; i--) {
      for (int j = patternSize - 1; j >= 0; j--) {
        boolean firstMatch = (i < textSize && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
        if (j + 1 < patternSize && p.charAt(j + 1) == '*') {
          dp[i][j] = dp[i][j + 2] || firstMatch && dp[i + 1][j];
        } else {
          dp[i][j] = firstMatch && dp[i + 1][j + 1];
        }
      }
    }

    return dp[0][0];
  }

  public boolean isMatchBFS(String s, String p) {
    int textSize = s.length();
    int patternSize = p.length();
    char[] text = s.toCharArray();
    char[] pat = p.toCharArray();

    // Queue contains 2 indices - start of s and start of p
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{0, 0});
    boolean[][] visited = new boolean[textSize + 1][patternSize + 1];
    visited[0][0] = true;

    while (!queue.isEmpty()) {
      int[] idx = queue.poll();
      if (idx[0] == textSize && idx[1] == patternSize) {
        return true;
      }
      // if not at pattern end
      if (idx[1] < patternSize) {
        if (!Character.isLetter(pat[idx[1]]) && pat[idx[1]] != '.') {
          continue;
        }
        // next char is *
        if (idx[1] + 1 < patternSize && pat[idx[1] + 1] == '*') {
          // 3 cases.
          // 1: move pattern ahead and keep string pointer as is.
          if (!visited[idx[0]][idx[1] + 2]) {
            queue.offer(new int[]{idx[0], idx[1] + 2});
            visited[idx[0]][idx[1] + 2] = true;
          }
          // 2: keep pattern as is and move string pointer if char matches.
          // 3: Move pattern and string both.
          if (idx[0] < textSize && (pat[idx[1]] == text[idx[0]] || pat[idx[1]] == '.')) {
            if (!visited[idx[0] + 1][idx[1]]) {
              queue.offer(new int[]{idx[0] + 1, idx[1]});
              visited[idx[0] + 1][idx[1]] = true;
            }
            if (!visited[idx[0] + 1][idx[1] + 2]) {
              queue.offer(new int[]{idx[0] + 1, idx[1] + 2});
              visited[idx[0] + 1][idx[1] + 2] = true;
            }
          }
        } else if (idx[0] < textSize && (pat[idx[1]] == text[idx[0]] || pat[idx[1]] == '.')) {
          if (!visited[idx[0] + 1][idx[1] + 1]) {
            queue.offer(new int[]{idx[0] + 1, idx[1] + 1});
            visited[idx[0] + 1][idx[1] + 1] = true;
          }
        }
      }
    }

    return false;
  }
}
