
// LC-1052
// https://leetcode.com/problems/grumpy-bookstore-owner/

public class GrumpyBookstoreOwner {

  public int maxSatisfied(int[] customers, int[] grumpy, int X) {
    int sum = 0;
    int satisfied = 0;
    int maxConverted = 0;

    for (int i = 0; i < customers.length; i++) {
      if (grumpy[i] == 0) {
        satisfied += customers[i];
        customers[i] = 0;
      }
      sum += i < X ? customers[i] : customers[i] - customers[i - X];
      maxConverted = Math.max(maxConverted, sum);
    }

    return satisfied + maxConverted;
  }
}
