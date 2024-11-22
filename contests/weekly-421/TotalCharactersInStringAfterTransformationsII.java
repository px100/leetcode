import java.util.List;

public class TotalCharactersInStringAfterTransformationsII {

  private static final int MOD = 1_000_000_007;

  public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
    long[] freq = new long[26];
    char[] charArray = s.toCharArray();
    for (char c : charArray) {
      freq[c - 'a']++;
    }
    long[][] transform = transform(nums);
    long[] values = applyTransform(transform, freq, t);
    long ans = 0;
    for (long value : values) {
      ans = (ans + value) % MOD;
    }
    return (int) ans;
  }

  private long[][] transform(List<Integer> nums) {
    long[][] mat = new long[26][26];
    for (int i = 0; i < 26; i++) {
      int k = nums.get(i);
      for (int j = 1; j <= k; j++) {
        mat[(i + j) % 26][i] = 1;
      }
    }
    return mat;
  }

  private long[] applyTransform(long[][] mat, long[] freq, long power) {
    long[][] pow = pow(mat, power);
    long[] res = new long[26];
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 26; j++) {
        res[i] = (res[i] + (pow[i][j] * freq[j]) % MOD) % MOD;
      }
    }
    return res;
  }

  private long[][] multiply(long[][] a, long[][] b) {
    long[][] res = new long[26][26];
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 26; j++) {
        for (int k = 0; k < 26; k++) {
          res[i][j] = (res[i][j] + (a[i][k] * b[k][j]) % MOD) % MOD;
        }
      }
    }
    return res;
  }

  private long[][] pow(long[][] mat, long pow) {
    long[][] res = new long[26][26];
    for (int i = 0; i < 26; i++) {
      res[i][i] = 1;
    }
    while (pow > 0) {
      if ((pow & 1) == 1) {
        res = multiply(res, mat);
      }
      mat = multiply(mat, mat);
      pow >>= 1;
    }
    return res;
  }
}
