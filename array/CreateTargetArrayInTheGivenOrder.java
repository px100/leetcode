
// LC-1389
// https://leetcode.com/problems/create-target-array-in-the-given-order/

import java.util.ArrayList;
import java.util.List;

public class CreateTargetArrayInTheGivenOrder {

  public int[] createTargetArray(int[] nums, int[] index) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      list.add(index[i], nums[i]);
    }
    return list.stream().mapToInt(num -> num).toArray();
  }
}
