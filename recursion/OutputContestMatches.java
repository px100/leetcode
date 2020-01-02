
// LC-544
// https://leetcode.com/problems/output-contest-matches/
// https://leetcode.com/articles/output-contest-matches/

public class OutputContestMatches {

  public String findContestMatch(int n) {
    String[] team = new String[n];
    for (int i = 1; i <= n; i++) {
      team[i - 1] = "" + i;
    }

    for (; n > 1; n >>= 1) {
      for (int i = 0; i < (n >> 1); i++) {
        team[i] = "(" + team[i] + "," + team[n - 1 - i] + ")";
      }
    }

    return team[0];
  }
}
