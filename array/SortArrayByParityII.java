
// LC-922
// https://leetcode.com/problems/sort-array-by-parity-ii/

public class SortArrayByParityII {

  // O(N) space
  public int[] sortArrayByParityII(int[] A) {
    int[] B = new int[A.length];
    final int[] counters = {-2, -1};

    for (int i = 0; i < B.length; i++) {
      B[counters[A[i] % 2] += 2] = A[i];
    }

    return B;

// A[i] % 2 returns either 0 or 1
// counters[0] or counters[1] contains count for even or odd pointers
// counters[j] += 2 first increments the count by 2 and then returns the value
// B[even_counter] or B[odd_counter] stores the actual A[i] value.
  }

  // O(1) space
  public int[] sortArrayByParityII2(int[] A) {
    int j = 1;
    for (int i = 0; i < A.length; i += 2) {
      if (A[i] % 2 == 1) {
        while (A[j] % 2 == 1) {
          j += 2;
        }
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
      }
    }

    return A;
  }
}
