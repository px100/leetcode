import java.util.LinkedList;
import java.util.List;

// LC-1104
// https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/¬

public class PathInZigzagLabelledBinaryTree {

  public List<Integer> pathInZigZagTree(int label) {
    List<Integer> result = new LinkedList<>();
    result.add(label);
    insertNum(result, label, Integer.highestOneBit(label));

    return result;
  }

  private void insertNum(List<Integer> numbers, int number, int startPoint) {
    if (number == 1) {
      return;
    }

    int index = (number - startPoint + 2) / 2;
    int nextNumber = startPoint - index;
    numbers.add(0, nextNumber);
    insertNum(numbers, nextNumber, Integer.highestOneBit(nextNumber));
  }
}
