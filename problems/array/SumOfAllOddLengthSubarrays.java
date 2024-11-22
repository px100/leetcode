
// LC-1588
// https://leetcode.com/problems/sum-of-all-odd-length-subarrays/

public class SumOfAllOddLengthSubarrays {

  // O(n)
  public int sumOddLengthSubarrays(int[] arr) {
    int n = arr.length;
    int totalSum = 0;
    for (int i = 0; i < n; i++) {
      int subArraysCount = (n - i) * (i + 1);
      int oddSubArraysCount = (subArraysCount - 1) / 2 + 1;
      totalSum += oddSubArraysCount * arr[i];
    }
    return totalSum;
  }

  public int sumOddLengthSubarrays2(int[] arr) {
    int n = arr.length;
    int[] prefixSum = new int[n + 1];
    for (int i = 0; i < n; i++) {
      prefixSum[i + 1] += prefixSum[i] + arr[i];
    }
    int totalSum = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        if ((j - i + 1) % 2 != 0) {
          totalSum += prefixSum[j + 1] - prefixSum[i];
        }
      }
    }
    return totalSum;
  }
}
