import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// LC-1122
// https://leetcode.com/problems/relative-sort-array/

public class RelativeSortArray {

  public int[] relativeSortArray(int[] arr1, int[] arr2) {
    Map<Integer, Integer> map = new TreeMap<>();
    for (int i : arr1) {
      map.merge(i, 1, Integer::sum);
    }

    int index = 0;
    for (int i : arr2) {
      for (int j = 0; j < map.get(i); j++) {
        arr1[index++] = i;
      }
      map.put(i, 0);
    }

    for (int i : map.keySet()) {
      if (map.get(i) != 0) {
        for (int j = 0; j < map.get(i); j++) {
          arr1[index++] = i;
        }
      }
    }

    return arr1;
  }


  public static class RelativeComparator implements Comparator<Integer> {

    int[] arr;

    public RelativeComparator(int[] arr) {
      this.arr = arr;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
      if (arr[o1] != -1 && arr[o2] != -1) {
        return arr[o1] - arr[o2];
      } else if (arr[o1] != -1) {
        return -1;
      } else if (arr[o2] != -1) {
        return 1;
      } else {
        return o1 - o2;
      }
    }
  }

  public int[] relativeSortArray2(int[] a, int[] b) {
    int[] arr = new int[1001];
    Arrays.fill(arr, -1);

    for (int i = 0; i < b.length; i++) {
      arr[b[i]] = i;
    }

    RelativeComparator relativeComparator = new RelativeComparator(arr);
    List<Integer> result = new ArrayList<>();
    for (int i1 : a) {
      result.add(i1);
    }
    result.sort(relativeComparator);

    return result.stream().mapToInt(i -> i).toArray();
  }
}
