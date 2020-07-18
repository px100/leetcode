import java.util.HashSet;
import java.util.Set;

// LC-421
// https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/

public class MaximumXorOfTwoNumbersInAnArray {

  public int findMaximumXORa(int[] nums) {
    int max = 0;
    int mask = 0;
    for (int i = 31; i >= 0; i--) {
      mask |= 1 << i;
      Set<Integer> set = new HashSet<>();
      for (int num : nums) {
        set.add(num & mask);
      }
      int nextMax = max | (1 << i);
      for (int prefix : set) {
        if (set.contains(prefix ^ nextMax)) {
          max = nextMax;
          break;
        }
      }
    }

    return max;
  }

  public int findMaximumXOR3(int[] nums) {
    if (nums.length < 2) {
      return 0;
    }
    int max = 0;
    int mask = 0;
    int allBits = 0;
    for (int num : nums) {
      allBits |= num;
    }
    int i = 30;
    for (; i >= 0; i--) {
      if ((allBits & (1 << i)) != 0) {
        break;
      }
    }
    for (; i >= 0; i--) {
      int bit = 1 << i;
      mask |= bit;
      int nextMax = max | bit;
      Set<Integer> seen = new HashSet<>();
      for (int num : nums) {
        num &= mask;
        if (seen.contains(num ^ nextMax)) {
          max = nextMax;
          break;
        }
        seen.add(num);
      }
    }

    return max;
  }
}
