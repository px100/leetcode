import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// LC-253
// https://leetcode.com/problems/meeting-rooms-ii/

public class MeetingRoomsII {

  public class Interval {

    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }

  // Sort given intervals by start time
  // Use a heap to get the interval with earliest end time.
  // Assign a new room if not able to merge two intervals together
  public int minMeetingRooms(Interval[] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(i -> i.start));
    PriorityQueue<Interval> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i.end));

    for (Interval interval : intervals) {
      Interval tmp = pq.poll();
      if (tmp == null || interval.start < tmp.end) {
        pq.offer(interval);
        if (tmp == null) {
          continue;
        }
      } else {
        tmp.end = interval.end;
      }
      pq.offer(tmp);
    }

    return pq.size();
  }

  // Put start time and end time into two arrays and sort them
  // Use a pointer to maintain minimum value of next end time as a simulation of poll() element in PriorityQueue
  public int minMeetingRooms2(Interval[] intervals) {
    int[] start = new int[intervals.length];
    int[] end = new int[intervals.length];

    for (int i = 0; i < intervals.length; i++) {
      start[i] = intervals[i].start;
      end[i] = intervals[i].end;
    }

    Arrays.sort(start);
    Arrays.sort(end);

    int room = 0;
    int ptr = 0;
    for (int value : start) {
      if (value < end[ptr]) {
        room++;
      } else {
        ptr++;
      }
    }

    return room;
  }
}
