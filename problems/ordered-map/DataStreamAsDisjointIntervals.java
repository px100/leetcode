import java.util.TreeMap;

// LC-352
// https://leetcode.com/problems/data-stream-as-disjoint-intervals/

public class DataStreamAsDisjointIntervals {

  static class SummaryRanges {

    TreeMap<Integer, Integer> map;

    /**
     * Initialize your data structure here.
     */
    public SummaryRanges() {
      map = new TreeMap<>();
    }

    public void addNum(int val) {
      if (map.containsKey(val)) {
        return;
      }
      int lowerBound = val;
      int upperBound = val;

      Integer lowerKey = map.lowerKey(val);
      if (lowerKey != null) {
        if (map.get(lowerKey) >= val) {
          return;
        }
        if (map.get(lowerKey) + 1 == val) {
          lowerBound = lowerKey;
          map.remove(lowerKey);
        }
      }
      Integer higherKey = map.higherKey(val);
      if (higherKey != null) {
        if (higherKey - 1 == val) {
          upperBound = map.get(higherKey);
          map.remove(higherKey);
        }
      }
      map.put(lowerBound, upperBound);
    }

    public int[][] getIntervals() {
      int[][] res = new int[map.size()][2];
      int i = 0;
      for (int key : map.keySet()) {
        res[i][0] = key;
        res[i++][1] = map.get(key);
      }
      return res;
    }
  }
}
