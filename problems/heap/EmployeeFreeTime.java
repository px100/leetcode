import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// LC-759
// https://leetcode.com/problems/employee-free-time/

public class EmployeeFreeTime {

  public static class Interval {

    int start;
    int end;

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }


  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    List<Interval> result = new ArrayList<>();
    List<Interval> tasks = new ArrayList<>();
    schedule.forEach(tasks::addAll);

    if (tasks.size() < 1) {
      return result;
    }

    tasks.sort(Comparator.comparingInt(a -> a.start));
    Interval prev = tasks.get(0);

    for (Interval cur : tasks) {
      if (prev.end < cur.start) {
        result.add(new Interval(prev.end, cur.start));
        prev = cur;
      } else {
        prev = prev.end < cur.end ? cur : prev;
      }
    }

    return result;
  }
}
