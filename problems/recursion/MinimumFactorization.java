
// LC-625
// https://leetcode.com/problems/minimum-factorization/
// https://leetcode.com/articles/minimum-factorization/

public class MinimumFactorization {

  // Time : O(8log(a))
  // Space : O(1)
  public int smallestFactorization(int a) {
    if (a < 2) {
      return a;
    }

    long res = 0;
    long mul = 1;
    for (int i = 9; i >= 2; i--) {
      while (a % i == 0) {
        a /= i;
        res = mul * i + res;
        mul *= 10;
      }
    }

    return a < 2 && res <= Integer.MAX_VALUE ? (int) res : 0;
  }
}
