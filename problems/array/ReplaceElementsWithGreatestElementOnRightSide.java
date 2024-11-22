
// LC-1299
// https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/

public class ReplaceElementsWithGreatestElementOnRightSide {

  public int[] replaceElements(int[] arr) {
    int size = arr.length;
    int max = arr[size - 1];

    arr[size - 1] = -1;
    for (int i = size - 2; i >= 0; i--) {
      int temp = arr[i];
      arr[i] = max;
      max = Math.max(max, temp);
    }

    return arr;
  }
}
