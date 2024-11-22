import java.util.HashMap;
import java.util.Map;

// LC-846
// https://leetcode.com/problems/hand-of-straights/

public class HandOfStraights {

  public boolean isNStraightHand(int[] hand, int W) {
    Map<Integer, Integer> freqMap = new HashMap<>();
    for (int i : hand) {
      freqMap.merge(i, 1, Integer::sum);
    }

    for (int num : hand) {
      if (freqMap.getOrDefault(num - 1, 0) <= 0 && freqMap.getOrDefault(num, 0) != 0) {
        int currNum = num;
        while (freqMap.getOrDefault(currNum, 0) > 0) {
          int count = 0;
          int newStartingNum = -1;
          while (count < W) {
            if (freqMap.getOrDefault(currNum, 0) == 0) {
              return false;
            }
            freqMap.merge(currNum, -1, Integer::sum);
            if (newStartingNum == -1 && freqMap.getOrDefault(currNum, 0) > 0) {
              newStartingNum = currNum;
            }
            currNum++;
            count++;
          }
          currNum = (newStartingNum == -1 ? currNum : newStartingNum);
        }
      }
    }

    return true;
  }
}
