import java.util.TreeSet;

// LC-683
// https://leetcode.com/problems/k-empty-slots/

public class KEmptySlots {

  // Time: O(n)
  // Space: O(n)
  public int kEmptySlots(int[] flowers, int k) {
    int[] days = new int[flowers.length];
    for (int i = 0; i < flowers.length; i++) {
      days[flowers[i] - 1] = i + 1;
    }
    int left = 0;
    int right = k + 1;
    int ans = Integer.MAX_VALUE;
    for (int i = 0; right < days.length; i++) {
      if (days[i] < days[left] || days[i] <= days[right]) {
        if (i == right) {
          ans = Math.min(ans, Math.max(days[left], days[right]));
        }
        left = i;
        right = k + 1 + i;
      }
    }

    return Integer.MAX_VALUE != ans ? ans : -1;
  }

  // Time: O(n*lg(n))
  // Space: O(n)
  public int kEmptySlots2(int[] flowers, int k) {
    int n = flowers.length;
    if (n == 1 && k == 0) {
      return 1;
    }

    TreeSet<Integer> sort = new TreeSet<>();
    for (int i = 0; i < n; i++) {
      sort.add(flowers[i]);
      Integer min = sort.lower(flowers[i]);
      Integer max = sort.higher(flowers[i]);
      if (min != null && flowers[i] - min == k + 1) {
        return i + 1;
      }
      if (max != null && max - flowers[i] == k + 1) {
        return i + 1;
      }
    }

    return -1;
  }
}
