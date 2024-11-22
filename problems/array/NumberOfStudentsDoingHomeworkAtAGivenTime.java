
// LC-1458
// https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time/

public class NumberOfStudentsDoingHomeworkAtAGivenTime {

  public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
    int count = 0;
    for (int i = 0; i < startTime.length; i++) {
      if (queryTime >= startTime[i] && queryTime <= endTime[i]) {
        count++;
      }
    }
    return count;
  }
}
