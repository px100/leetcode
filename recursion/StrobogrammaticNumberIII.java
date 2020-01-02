import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// LC-248
// https://leetcode.com/problems/strobogrammatic-number-iii/

public class StrobogrammaticNumberIII {

  public int strobogrammaticInRange(String low, String high) {
    List<String> result = new ArrayList<>();
    for (int i = low.length(); i <= high.length(); i++) {
      result.addAll(helper(i, i));
    }

    int count = 0;
    for (String s : result) {
      if ((s.length() == low.length() && s.compareTo(low) < 0)
        || (s.length() == high.length() && s.compareTo(high) > 0)) {
        continue;
      }
      count++;
    }

    return count;
  }

  private List<String> helper(int targetLen, int totalLen) {
    if (targetLen == 0) {
      return new ArrayList<>(Collections.singletonList(""));
    }
    if (targetLen == 1) {
      return new ArrayList<>(Arrays.asList("1", "8", "0"));
    }

    List<String> prev = helper(targetLen - 2, totalLen);
    List<String> result = new ArrayList<>();
    for (String str : prev) {
      if (targetLen != totalLen) {
        result.add("0" + str + "0");
      }
      result.add("1" + str + "1");
      result.add("6" + str + "9");
      result.add("9" + str + "6");
      result.add("8" + str + "8");
    }

    return result;
  }
}
