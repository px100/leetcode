import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

// LC-731
// https://leetcode.com/problems/my-calendar-ii/

public class MyCalendarII {

  private static class MyCalendarTwo {

    TreeMap<Integer, Integer> events;
    TreeMap<Integer, Integer> overlaps;

    public MyCalendarTwo() {
      events = new TreeMap<>();
      overlaps = new TreeMap<>();
    }

    public boolean book(int start, int end) {
      Integer key = overlaps.floorKey(end - 1);
      if (key != null && overlaps.get(key) > start) {
        return false;
      }

      key = events.floorKey(end - 1);
      while (key != null) {
        if (events.get(key) > start) {
          overlaps.put(Math.max(key, start), Math.min(events.get(key), end));
          start = Math.min(key, start);
          end = Math.max(events.get(key), end);
          events.remove(key);
          key = events.floorKey(end - 1);
        } else {
          break;
        }
      }
      events.put(start, end);
      return true;
    }
  }

  private static class MyCalendarTwoLists {

    List<int[]> events;
    List<int[]> overlaps;

    public MyCalendarTwoLists() {
      this.events = new ArrayList<>();
      this.overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
      if (overlaps.stream().anyMatch(overlap -> overlap[0] < end && start < overlap[1])) {
        return false;
      }

      events.stream()
          .filter(event -> event[0] < end && start < event[1])
          .forEach(event -> overlaps.add(
              new int[]{Math.max(start, event[0]), Math.min(end, event[1])}));
      events.add(new int[]{start, end});
      return true;
    }
  }
}
