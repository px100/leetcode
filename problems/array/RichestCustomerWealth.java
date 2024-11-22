
// LC-1672
// https://leetcode.com/problems/richest-customer-wealth/

public class RichestCustomerWealth {

  public int maximumWealth(int[][] accounts) {
    int max = Integer.MIN_VALUE;
    for (int[] account : accounts) {
      int wealth = 0;
      for (int money : account) {
        wealth += money;
      }
      max = Math.max(max, wealth);
    }
    return max;
  }
}
