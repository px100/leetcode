import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

// LC-451
// https://leetcode.com/problems/sort-characters-by-frequency/

public class SortCharactersByFrequency {

  public String frequencySort(String s) {
    int[] helper = new int[128];
    int max = 0;
    for (char c : s.toCharArray()) {
      max = Math.max(++helper[c], max);
    }

    char[] rs = new char[s.length()];
    int j = 0;
    while (max > 0) {
      int lastMax = 0;
      for (int i = 0; i < 128; i++) {
        if (helper[i] == max) {
          while (helper[i]-- > 0) {
            rs[j++] = ((char) i);
          }
        } else {
          lastMax = Math.max(helper[i], lastMax);
        }
      }
      max = lastMax;
    }

    return new String(rs);
  }

  public String frequencySort2(String s) {
    if (s.isEmpty()) {
      return s;
    }

    Map<Character, Integer> map = new HashMap<>();
    int bound = s.length();
    for (int i = 0; i < bound; i++) {
      map.merge(s.charAt(i), 1, Integer::sum);
    }

    List<Entry<Character, Integer>> toSort = new ArrayList<>(map.entrySet());
    toSort.sort(Entry.comparingByValue(Comparator.reverseOrder()));

    Map<Character, Integer> result = toSort.stream()
      .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> a, LinkedHashMap::new));

    StringBuilder sb = new StringBuilder();
    result.forEach((key, value) -> {
      for (int i = 0; i < value; i++) {
        sb.append(key);
      }
    });

    sb.trimToSize();
    return sb.toString();
  }
}
