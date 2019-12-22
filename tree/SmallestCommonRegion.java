import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// LC-1257
// https://leetcode.com/problems/smallest-common-region/

public class SmallestCommonRegion {

  public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
    if (regions.isEmpty()) {
      return null;
    }

    Map<String, String> parent = new HashMap<>();
    regions.forEach(region -> {
      for (String r : region) {
        if (!parent.containsKey(r) || parent.get(r).equals(r)) {
          parent.put(r, region.get(0));
        }
      }
    });

    Set<String> visited = new HashSet<>();
    visited.add(region1);

    String p = parent.get(region1);
    while (!region1.equals(p)) {
      visited.add(p);
      region1 = p;
      p = parent.get(region1);
    }

    String result = region2;
    while (!visited.contains(result)) {
      result = parent.get(result);
    }

    return result;
  }
}
