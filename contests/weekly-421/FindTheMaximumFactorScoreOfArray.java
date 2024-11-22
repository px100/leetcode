public class FindTheMaximumFactorScoreOfArray {

  public long maxScore(int[] nums) {
    int size = nums.length;
    if (size == 0) {
      return 0;
    }
    long[] gcdLcmAll = getGcdLcm(nums, -1);
    long scoreAll = gcdLcmAll[0] * gcdLcmAll[1];
    long ans = Math.max(0, scoreAll);
    for (int i = 0; i < size; i++) {
      long[] gcdLcmExcl = getGcdLcm(nums, i);
      long scoreExcl = gcdLcmExcl[0] * gcdLcmExcl[1];
      ans = Math.max(ans, scoreExcl);
    }
    return ans;
  }

  private long lcm(long a, long b) {
    return (a / gcd(a, b)) * b;
  }

  private long gcd(long a, long b) {
    while (b != 0) {
      long temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  private long[] getGcdLcm(int[] nums, int index) {
    int size = nums.length;
    if (index >= 0 && size == 1 || size == 0) {
      return new long[]{0, 0};
    }
    long gcd = -1;
    long lcm = 1;
    for (int i = 0; i < size; i++) {
      if (i != index) {
        int num = nums[i];
        if (gcd != -1) {
          gcd = gcd(gcd, num);
          lcm = lcm(lcm, num);
        } else {
          gcd = num;
          lcm = num;
        }
      }
    }
    return gcd == -1
        ? new long[]{0, 0}
        : new long[]{gcd, lcm};
  }
}
