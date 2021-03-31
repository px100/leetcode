
// LC-1486
// https://leetcode.com/problems/xor-operation-in-an-array/

public class XOROperationInAnArray {

  public int xorOperation(int n, int start) {
    int ans = 0;
    for (int i = 0; i < n; i++) {
      ans ^= start + (i << 1);
    }
    return ans;
  }
}
