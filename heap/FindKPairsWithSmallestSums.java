import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// LC-373
// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/

public class FindKPairsWithSmallestSums {

  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    PriorityQueue<List<Integer>> pq = new PriorityQueue<>(
      (List<Integer> e1, List<Integer> e2) -> e2.get(1) + e2.get(0) - e1.get(1) - e1.get(0));

    for (int item : nums1) {
      for (int value : nums2) {
        pq.offer(Arrays.asList(item, value));
        if (pq.size() > k) {
          pq.poll();
        }
      }
    }

    return new ArrayList<>(pq);
  }

  public List<List<Integer>> kSmallestPairsFaster(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k <= 0) {
      return result;
    }

    int nums1Length = nums1.length;
    int nums2Length = nums2.length;

    PriorityQueue<int[]> pq = new PriorityQueue<>(k, Comparator.comparingInt(a -> a[0] + a[1]));

    for (int i = 0; i < nums1Length && i < k; i++) {
      pq.add(new int[]{nums1[i], nums2[0], 0});
    }

    int range = nums1Length * nums2Length;
    for (int j = 0; j < k && j < range; j++) {
      int[] cur = pq.poll();
      result.add(Arrays.asList(cur[0], cur[1]));
      if (cur[2] < nums2Length - 1) {
        int idx = cur[2] + 1;
        pq.offer(new int[]{cur[0], nums2[idx], idx});
      }
    }

    return result;
  }
}
