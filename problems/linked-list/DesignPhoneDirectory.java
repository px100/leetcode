import java.util.LinkedHashSet;

// LC-379
// https://leetcode.com/problems/design-phone-directory/

public class DesignPhoneDirectory {

  private static class PhoneDirectory {

    LinkedHashSet<Integer> availableNumbers;
    int availableCount;

    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public PhoneDirectory(int maxNumbers) {
      availableNumbers = new LinkedHashSet<>(maxNumbers, 1.0f);
      for (int i = 0; i < maxNumbers; i++) {
        availableNumbers.add(i);
      }
      availableCount = maxNumbers;
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
      if (availableCount == 0) {
        return -1;
      }

      availableCount--;
      int retVal = availableNumbers.iterator().next();
      availableNumbers.remove(retVal);

      return retVal;
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
      return availableNumbers.contains(number);
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
      if (!availableNumbers.contains(number)) {
        availableNumbers.add(number);
        availableCount++;
      }
    }
  }
}
