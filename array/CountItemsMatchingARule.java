
// LC-1773
// https://leetcode.com/problems/count-items-matching-a-rule/

import java.util.List;

public class CountItemsMatchingARule {

  public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
    int ans = 0;
    switch (ruleKey) {
      case "type":
        for (List<String> item : items) {
          if (item.get(0).equals(ruleValue)) {
            ans++;
          }
        }
        break;
      case "color":
        for (List<String> item : items) {
          if (item.get(1).equals(ruleValue)) {
            ans++;
          }
        }
        break;
      case "name":
        for (List<String> item : items) {
          if (item.get(2).equals(ruleValue)) {
            ans++;
          }
        }
        break;
    }
    return ans;
  }
}
