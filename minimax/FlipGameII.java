import java.util.HashMap;
import java.util.Map;

// LC-294
// https://leetcode.com/problems/flip-game-ii/

public class FlipGameII {

  private Map<String, Boolean> map = new HashMap<>();

  public boolean canWin(String s) {
    if (s == null || s.length() == 0) {
      return false;
    }

    if (map.containsKey(s)) {
      return map.get(s);
    }

    char[] tokens = s.toCharArray();
    for (int i = 0; i < tokens.length - 1; i++) {
      if (tokens[i] == '+' && tokens[i + 1] == '+') {
        tokens[i] = '-';
        tokens[i + 1] = '-';

        boolean canWin = !canWin(new String(tokens));

        tokens[i] = '+';
        tokens[i + 1] = '+';

        if (canWin) {
          map.put(new String(tokens), true);
          return true;
        }
      }
    }
    map.put(new String(tokens), false);

    return false;
  }
}
