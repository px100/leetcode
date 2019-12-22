import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// LC-582
// https://leetcode.com/problems/kill-process/
// https://leetcode.com/articles/kill-process/

public class KillProcess {

  public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
    List<Integer> result = new ArrayList<>();
    Map<Integer, List<Integer>> map = new HashMap<>();

    for (int i = 0; i < ppid.size(); i++) {
      if (ppid.get(i) > 0) {
        List<Integer> l = map.getOrDefault(ppid.get(i), new ArrayList<>());
        l.add(pid.get(i));
        map.put(ppid.get(i), l);
      }
    }

    Queue<Integer> queue = new LinkedList<>();
    queue.add(kill);
    while (!queue.isEmpty()) {
      int r = queue.remove();
      result.add(r);
      if (map.containsKey(r)) {
        queue.addAll(map.get(r));
      }
    }

    return result;
  }
}
