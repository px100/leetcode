
// LC-1656
// https://leetcode.com/problems/design-an-ordered-stream/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignAnOrderedStream {

  class OrderedStream {

    private int k;
    private final Map<Integer, String> map;

    public OrderedStream(int n) {
      this.k = 1;
      this.map = new HashMap<>();
    }

    public List<String> insert(int idKey, String value) {
      map.put(idKey, value);
      List<String> result = new ArrayList<>();
      while (true) {
        if (map.containsKey(k)) {
          result.add(map.get(k++));
        } else {
          break;
        }
      }
      return result;
    }
  }

  /**
   * Your OrderedStream object will be instantiated and called as such:
   * OrderedStream obj = new OrderedStream(n);
   * List<String> param_1 = obj.insert(idKey,value);
   */
}
