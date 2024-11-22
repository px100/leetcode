
// LC=941
// https://leetcode.com/problems/valid-mountain-array/

public class ValidMountainArray {

  public boolean validMountainArray(int[] arr) {
    int left = 0;
    int right = arr.length - 1;
    while (left < right) {
      if (arr[left] < arr[left + 1]) {
        left++;
      } else if (arr[right] < arr[right - 1]) {
        right--;
      } else {
        return false;
      }

      if (left == right) {
        return left != 0 && left != arr.length - 1;
      }
    }
    return false;
  }
}
