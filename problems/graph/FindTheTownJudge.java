import java.util.HashSet;
import java.util.Set;

// LC-997
// https://leetcode.com/problems/find-the-town-judge/

public class FindTheTownJudge {

  public int findJudge(int N, int[][] trust) {
    int[] possibleJudge = new int[N];
    for (int[] t : trust) {
      possibleJudge[t[0] - 1]--;
      possibleJudge[t[1] - 1]++;
    }

    for (int i = 0; i < N; i++) {
      if (possibleJudge[i] == N - 1) {
        return i + 1;
      }
    }

    return -1;
  }

  public int findJudge2(int N, int[][] trust) {
    Set<Integer> notJudge = new HashSet<>();

    int candidate = 1;
    for (int[] t : trust) {
      notJudge.add(t[0]);
      if (candidate == t[0] && !notJudge.contains(t[1])) {
        candidate = t[1];
      }
    }

    int count = 0;
    for (int[] t : trust) {
      if (t[1] == candidate) {
        count++;
      }
    }

    if (count == N - 1 && !notJudge.contains(candidate)) {
      return candidate;
    }

    return -1;
  }
}
