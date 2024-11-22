import java.util.Arrays;

public class TotalCharactersInStringAfterTransformationsI {

  private static final long MOD = 1_000_000_007;

  public int lengthAfterTransformations(String s, int t) {
    final long[] cur = new long[26];
    final long[] nxt = new long[26];
    s.chars().forEach(c -> cur[c - 'a'] = (cur[c - 'a'] + 1) % MOD);
    for (int i = 0; i < t; i++) {
      Arrays.fill(nxt, 0);
      for (int c = 0; c < 26; c++) {
        if (c == 25) {
          nxt[0] = (nxt[0] + cur[c]) % MOD;
          nxt[1] = (nxt[1] + cur[c]) % MOD;
        } else {
          nxt[c + 1] = (nxt[c + 1] + cur[c]) % MOD;
        }
      }
      System.arraycopy(nxt, 0, cur, 0, 26);
    }
    return (int) Arrays.stream(cur).reduce(0, (a, b) -> (a + b) % MOD);
  }
}
