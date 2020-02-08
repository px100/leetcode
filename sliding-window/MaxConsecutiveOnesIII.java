
// LC-1004
// https://leetcode.com/problems/max-consecutive-ones-iii/

import java.util.LinkedList;
import java.util.Queue;

public class MaxConsecutiveOnesIII {

  public int longestOnes(int[] A, int K) {
    int i = 0;
    int j = 0;

    while (i < A.length) {
      K -= 1 - A[i++];
      if (K < 0) {
        K += 1 - A[j++];
      }
    }

    return i - j;
  }

  public int longestOnesQueue(int[] A, int K) {
    int n = 0;
    int t = K;
    int maxOnes = Integer.MIN_VALUE;

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < A.length; i++) {
      if (A[i] == 0) {
        queue.add(i);
        t--;
      }
      if (A[i] == 0 && t == -1) {
        maxOnes = Math.max(maxOnes, n);
        n = i - queue.poll();
        t = 0;
      } else {
        n++;
      }
    }

    return Math.max(n, maxOnes);
  }
}
