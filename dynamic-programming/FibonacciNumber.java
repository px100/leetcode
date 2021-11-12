
// LC-509
// https://leetcode.com/problems/fibonacci-number/

public class FibonacciNumber {

  // TC: O(N)
  // SC: O(1)
  public int fib(int n) {
    if (n <= 1) {
      return n;
    }
    if (n == 2) {
      return 1;
    }

    int cur = 0;
    int prev1 = 1;
    int prev2 = 1;

    for (int i = 3; i <= n; i++) {
      cur = prev1 + prev2;
      prev2 = prev1;
      prev1 = cur;
    }
    return cur;
  }
}
