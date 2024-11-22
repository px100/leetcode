
// LC-292
// https://leetcode.com/problems/nim-game/

public class NimGame {

  public boolean canWinNim(int n) {
    return (n & 3) != 0; // (n % 4 != 0)
  }
}
