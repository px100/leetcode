import java.util.ArrayList;
import java.util.List;

// LC-1622
// https://leetcode.com/problems/fancy-sequence/

public class FancySequence {

  // compute value only when requested.
  // track changes using two variables `add` and `mult`.
  // final result is (list[i] * mult + add).
  class Fancy {

    private final static int MOD = 1000000007;

    private List<Long> list;
    private long mult = 1;
    private long add = 0;

    private long modPow(long x, long y) {
      long tot = 1;
      long p = x;
      for (; y > 0; y >>= 1) {
        if ((y & 1) == 1) {
          tot = (tot * p) % MOD;
        }
        p = (p * p) % MOD;
      }
      return tot;
    }

    // use fermat's last theorem
    private long modInv(long x) {
      return modPow(x, MOD - 2);
    }

    public Fancy() {
      list = new ArrayList<>(123456);
    }

    // negate final formula as resetting `mult` and `add` will result in TLE.
    // so we do 8(newVal -= add) and (newVal /= mult)
    // Since these are in modulo arithmetic division changes to (newVal *= modInv(mult))
    public void append(int val) {
      list.add(((MOD + val - add) * modInv(mult)) % MOD);
    }

    // just do add += val
    public void addAll(int inc) {
      add = (add + inc % MOD) % MOD;
    }

    // original value before multiplication is `org`
    // org = list[i] * mult + add
    // after multiplying by m, it becomes
    // org = list[i] * (mult * m) + (add * m).
    // which is nothing but (mult *= m) and (add *= m)
    public void multAll(int m) {
      mult = (mult * m % MOD) % MOD;
      add = (add * m % MOD) % MOD;
    }

    public int getIndex(int idx) {
      if (idx >= list.size()) {
        return -1;
      }
      return (int) ((list.get(idx) * mult) % MOD + add) % MOD;
    }
  }

  /**
   * Your Fancy object will be instantiated and called as such:
   * Fancy obj = new Fancy();
   * obj.append(val);
   * obj.addAll(inc);
   * obj.multAll(m);
   * int param_4 = obj.getIndex(idx);
   */
}
