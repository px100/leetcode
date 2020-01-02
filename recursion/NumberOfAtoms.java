import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// LC-726
// https://leetcode.com/problems/number-of-atoms/

public class NumberOfAtoms {

  public String countOfAtoms(String formula) {
    Map<String, Integer> map = new HashMap<>();
    ArrayDeque<Integer> factors = new ArrayDeque<>();

    int factor = 1;
    StringBuilder sb = new StringBuilder();
    for (int i = formula.length() - 1; i >= 0; i--) {
      char c = formula.charAt(i);
      if (Character.isUpperCase(c)) {
        int count = sb.length() == 0 ? 1 : Integer.parseInt(sb.toString());
        map.merge("" + c, count * factor, Integer::sum);
        sb = new StringBuilder();
      } else if (Character.isLowerCase(c)) {
        int count = sb.length() == 0 ? 1 : Integer.parseInt(sb.toString());
        map.merge(formula.charAt(--i) + "" + c, count * factor, Integer::sum);
        sb = new StringBuilder();
      } else if (Character.isDigit(c)) {
        sb.insert(0, c);
      } else if (c == ')') {
        int count = Integer.parseInt(sb.toString());
        factor *= count;
        factors.push(count);
        sb = new StringBuilder();
      } else {
        factor /= factors.pop();
      }
    }

    List<String> list = new ArrayList<>(map.keySet());
    Collections.sort(list);

    return list.stream()
      .map(str -> str + (map.get(str) > 1 ? map.get(str) : ""))
      .collect(Collectors.joining());
  }
}
