import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// LC-802
// https://leetcode.com/problems/find-eventual-safe-states/

public class FindEventualSafeStates {

  public List<Integer> eventualSafeNodesDfs(int[][] graph) {
    Set<Integer> visited = new HashSet<>();
    Set<Integer> visiting = new HashSet<>();

    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < graph.length; i++) {
      if (!dfs(i, graph, visiting, visited)) {
        result.add(i);
      }
    }
    return result;
  }

  private boolean dfs(int cur, int[][] graph, Set<Integer> visiting, Set<Integer> visited) {
    visiting.add(cur);
    for (int next : graph[cur]) {
      if (visiting.contains(next)) {
        return true;
      } else if (!visited.contains(next)) {
        visited.add(next);
        if (dfs(next, graph, visiting, visited)) {
          return true;
        }
      }
    }
    visiting.remove(cur);
    return false;
  }

  //////////////////////////////////////////////////////////////////////////////////////////

  private enum Color {
    Gray,  // visiting
    White, // unvisited
    Black; // visited
  }

  // topological dfs with 3 colors
  public List<Integer> eventualSafeNodes(int[][] graph) {
    List<Integer> result = new ArrayList<>();
    if (graph.length == 0) {
      return result;
    }

    Color[] colors = new Color[graph.length];
    Arrays.fill(colors, Color.White);

    for (int i = 0; i < graph.length; i++) {
      if (!hasCycle(i, graph, colors)) {
        result.add(i);
      }
    }
    return result;
  }

  private boolean hasCycle(int cur, int[][] graph, Color[] colors) {
    colors[cur] = Color.Gray;
    for (int next : graph[cur]) {
      if (colors[next] == Color.Gray) {
        return true;
      }
      if (colors[next] == Color.White) {
        if (hasCycle(next, graph, colors)) {
          return true;
        }
      }
    }
    colors[cur] = Color.Black;
    return false;
  }
}
