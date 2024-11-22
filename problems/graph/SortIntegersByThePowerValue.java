import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// LC-1387
// https://leetcode.com/problems/sort-integers-by-the-power-value/

public class SortIntegersByThePowerValue {

  Map<Integer, Integer> map = new HashMap<>();

  public int getKth(int lo, int hi, int k) {
    map.put(1, 0);

    return IntStream.rangeClosed(lo, hi)
        .mapToObj(i -> new int[]{i, collatz(i)})
        .sorted(Comparator.comparingInt(a -> a[1]))
        .collect(Collectors.toList())
        .get(k - 1)[0];
  }

  private int collatz(int x) {
    if (map.containsKey(x)) {
      return map.get(x);
    }
    if (x == 1) {
      return 1;
    }

    int result = x % 2 == 1 ? collatz(x * 3 + 1) + 1 : collatz(x >> 1) + 1;
    map.put(x, result);

    return result;
  }
}
