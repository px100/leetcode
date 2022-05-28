import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {

  private record Edge(int s, int d) {

  }

  public int minReorder(int n, int[][] connections) {
    Set<Edge> set = new HashSet<>();
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int[] connection : connections) {
      int x = connection[0];
      int y = connection[1];
      map.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
      map.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
      set.add(new Edge(x, y));
    }

    Queue<Integer> queue = new LinkedList<>();
    queue.offer(0);
    boolean[] visited = new boolean[n];
    visited[0] = true;
    int ans = 0;
    while (!queue.isEmpty()) {
      int cur = queue.poll();
      for (int next : map.getOrDefault(cur, List.of())) {
        if (!visited[next]) {
          if (!set.contains(new Edge(next, cur))) {
            ans++;
          }
          visited[next] = true;
          queue.offer(next);
        }
      }
    }
    return ans;
  }
}
