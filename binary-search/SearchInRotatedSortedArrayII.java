
// LC-81
// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

public class SearchInRotatedSortedArrayII {

  // TC: O(log(n))
  public boolean search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return false;
    }

    int i = 0;
    int j = nums.length - 1;
    while (i <= j) {
      int m = i + (j - i) / 2;
      if (nums[m] == target) {
        return true;
      }
      if (nums[m] == nums[i]) { // duplicate; skip
        i++;
        // 'bigger' increasing side, like [5,5,7] from [5,5,7,0,1,3,4]
      } else if (nums[m] > nums[i]) {
        if (nums[m] > target && nums[i] <= target) {
          // if target is between nums[i] and nums[m], set the right border to m-1
          j = m - 1;
        } else {
          // if target is NOT between nums[i] and nums[m] then target is on the 'right' side of m;
          // limit the 'left' border [i=m+1]
          i = m + 1;
        }
        // 'smaller' increasing side, like [0,1,3,4] from [5,5,7,0,1,3,4]
      } else {
        if (nums[m] < target && nums[j] >= target) {
          // if target is between nums[m] and nums[j], it makes sense to set the left border to m+1
          i = m + 1;
        } else {
          // if target is NOT between nums[m] and nums[j] then target is on the 'left' side of m;
          // limit the 'right' border [j=m-1]
          j = m - 1;
        }
      }
    }
    return false;
  }

  public int atMostNGivenDigitSet(String[] digits, int n) {
    char[] N = Integer.toString(n).toCharArray();
    int len = N.length;
    int digitsLength = digits.length;
    int res = 1;
    int smaller = -1;

    for (int i = 1, m = 1; i <= len; i++, m *= digitsLength) {
      int pres = res;
      res = 0;
      int x = N[len - i] - '0';
      for (String d : digits) {
        if (Integer.parseInt(d) < x) {
          res += m;
        } else if (Integer.parseInt(d) == x) {
          res += pres;
        }
      }
      smaller += m;
    }
    return res + smaller;
  }

  public int atMostNGivenDigitSeth(String[] D, int n) {
    int[] digits = new int[D.length];
    for (int i = 0; i < D.length; i++) {
      digits[i] = D[i].charAt(0) - '0';
    }
    int count = 0;
    for (int c = (int) (Math.log10(n) + 1); c > 0; c--) {  // (N > 0 won't cover e.g. 100)
      int order = (int) Math.pow(10, c - 1);
      int x = n / order;
      int mul = 0;
      boolean xInSet = false;
      for (int d : digits) {
        if (d <= x) {
          mul++;
        }
        if (d == x) {
          xInSet = true;
        }
      }
      count += (Math.pow(D.length, c - 1)) * mul;
      n = xInSet ? n % order : order - 1;   // e.g. 234 -> 34 | 99
    }
    return count;
  }
}
