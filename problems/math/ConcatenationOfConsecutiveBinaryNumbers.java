
// LC-1680
// https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/

public class ConcatenationOfConsecutiveBinaryNumbers {

//  Let f(n) be the answer. sum_len(a, b) = sum( len(i) | a <= i <= b) and len(i) is the number of bits in i.
//
//  For example: len(1) = 1, len(3) = 2, len(6) = 3.
//               sum_len(1,4) = len(1) + len(2) + len(3) + len(4) = 1 + 2 + 2 + 3 = 8.
//
//  So we have
//
//  f(n)   = (1 << sum_len(2, n))   + (2 << sum_len(3, n))   + ... + ((n - 1) << sum_len(n, n)) + (n << 0)
//
//  Example: f(4) = 11011100 = (1 << (2+2+3)) + (2 << (2+3)) + (3 << 3) + (4 << 0)
//
//  f(n-1) = (1 << sum_len(2, n-1)) + (2 << sum_len(3, n-1)) + ... + ((n - 1) << 0)
//
//  f(n) = (f(n-1) << len(n)) + n
//
//  Since f(0) = 0, we can iteratively build f(n).
//
//  f(0) = 0
//  f(1) = 1     = (f(0) << 1) + 1  // len(1) = 1
//  f(2) = 110   = (f(1) << 2) + 2  // len(2) = 2
//  f(3) = 11011 = (f(2) << 2) + 3  // len(3) = 2
//  ...

  private final static int MOD = (int) (1e9 + 7);

  public int concatenatedBinary(int n) {
    long sum = 0;
    for (int i = 1; i <= n; i++) {
      // len only increment when the i is a power of 2
      // if ((i & (i - 1) == 0) len++;
      int len = 32 - Integer.numberOfLeadingZeros(i);
      sum = ((sum << len) % MOD + i) % MOD;
    }
    return (int) sum;
  }
}
