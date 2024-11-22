import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// LC-761
// https://leetcode.com/problems/special-binary-string/

public class SpecialBinaryString {

  public String makeLargestSpecial(String S) {
    int count = 0;
    int i = 0;
    List<String> result = new ArrayList<>();
    for (int j = 0; j < S.length(); j++) {
      if (S.charAt(j) == '1') {
        count++;
      } else {
        count--;
      }
      if (count == 0) {
        result.add('1' + makeLargestSpecial(S.substring(i + 1, j)) + '0');
        i = j + 1;
      }
    }
    result.sort(Collections.reverseOrder());

    return String.join("", result);
  }
}
