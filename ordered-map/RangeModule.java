import java.util.TreeMap;

// LC-715
// https://leetcode.com/problems/range-module/

public class RangeModule {

  TreeMap<Integer, Boolean> map;

  public RangeModule() {
    map = new TreeMap<>();
    map.put(0, false);
  }

  public void addRange(int left, int right) {
    boolean from = map.get(map.lowerKey(left));
    boolean to = map.get(map.floorKey(right));
    map.subMap(left, true, right, true).clear();
    if (!from) {
      map.put(left, true);
    }
    if (!to) {
      map.put(right, false);
    }
  }

  public boolean queryRange(int left, int right) {
    int lower = map.lowerKey(right);
    return lower <= left && map.get(lower);
  }

  public void removeRange(int left, int right) {
    boolean from = map.get(map.lowerKey(left));
    boolean to = map.get(map.floorKey(right));
    map.subMap(left, true, right, true).clear();
    if (from) {
      map.put(left, false);
    }
    if (to) {
      map.put(right, true);
    }
  }
}
