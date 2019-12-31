import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// LC-1054
// https://leetcode.com/problems/distant-barcodes/

public class DistantBarcodes {

  public int[] rearrangeBarcodes(int[] barcodes) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int barcode : barcodes) {
      map.merge(barcode, 1, Integer::sum);
    }

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
    for (int key : map.keySet()) {
      pq.add(new int[]{key, map.get(key)});
    }

    int n = barcodes.length;
    int[] result = new int[n];
    for (int i = 0; i < n; ) {
      int[] cur = pq.poll();
      result[i++] = cur[0];
      if (pq.isEmpty()) {
        break;
      }
      int[] next = pq.poll();
      result[i++] = next[0];
      if (cur[1] != 1) {
        pq.add(new int[]{cur[0], cur[1] - 1});
      }
      if (next[1] != 1) {
        pq.add(new int[]{next[0], next[1] - 1});
      }
    }

    return result;
  }
}
