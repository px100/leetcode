import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// LC-220
// https://leetcode.com/problems/contains-duplicate-iii/

public class ContainsDuplicateIII {

  // O(N)
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (k < 1 || t < 0) {
      return false;
    }
    Map<Long, Long> buckets = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      long num = (long) nums[i] - Integer.MIN_VALUE;
      long bucketId = num / ((long) t + 1);
      if (buckets.containsKey(bucketId)) {
        return true;
      }
      if (buckets.containsKey(bucketId - 1) && num - buckets.get(bucketId - 1) <= t) {
        return true;
      }
      if (buckets.containsKey(bucketId + 1) && buckets.get(bucketId + 1) - num <= t) {
        return true;
      }
      if (buckets.size() == k) {
        long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
        buckets.remove(lastBucket);
      }
      buckets.put(bucketId, num);
    }
    return false;
  }

  // O(n*log(n))
  public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
    TreeSet<Integer> set = new TreeSet<>();
    for (int i = 0; i < nums.length; i++) {
      // Find the successor of current element
      Integer s = set.ceiling(nums[i]);
      if (s != null && s <= nums[i] + t) {
        return true;
      }
      // Find the predecessor of current element
      Integer p = set.floor(nums[i]);
      if (p != null && nums[i] <= p + t) {
        return true;
      }
      set.add(nums[i]);
      if (set.size() > k) {
        set.remove(nums[i - k]);
      }
    }
    return false;
  }
}
