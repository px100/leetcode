import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// LC-247
// https://leetcode.com/problems/strobogrammatic-number-ii/

public class StrobogrammaticNumberII {

  // Time  : O(5^n))
  // Space : O(n/2)
  public List<String> findStrobogrammatic(int n) {
    return helper(n, n);
  }

  public List<String> helper(int targetLen, int totalLen) {
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
