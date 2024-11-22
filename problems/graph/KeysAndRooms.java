import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// LC-841
// https://leetcode.com/problems/keys-and-rooms/

public class KeysAndRooms {

  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    if (rooms.isEmpty()) {
      return true;
    }

    Set<Integer> visited = new HashSet<>();
    visited.add(0);

    Queue<Integer> queue = new LinkedList<>();
    queue.offer(0);
    while (!queue.isEmpty()) {
      if (visited.size() == rooms.size()) {
        return true;
      }
      for (int nextKey : rooms.get(queue.poll())) {
        if (!visited.contains(nextKey)) {
          visited.add(nextKey);
          queue.add(nextKey);
        }
      }
    }

    return false;
  }
}
