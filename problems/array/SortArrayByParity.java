import java.util.Arrays;
import java.util.Comparator;

// LC-905
// https://leetcode.com/problems/sort-array-by-parity/

public class SortArrayByParity {

  public int[] sortArrayByParity(int[] A) {
    int size = A.length;
    int lastEvenIndex = 0;
    for (int i = 0; i < size; i++) {
      if (A[i] % 2 == 0) {
        int temp = A[lastEvenIndex];
        A[lastEvenIndex++] = A[i];
        A[i] = temp;
      }
    }

    return A;
  }

  public int[] sortArrayByParitySort(int[] A) {
    return Arrays.stream(A)
      .boxed()
      .sorted(Comparator.comparingInt(i -> i % 2))
      .mapToInt(i -> i)
      .toArray();
  }
}
