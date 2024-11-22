import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// LC-797
// https://leetcode.com/problems/all-paths-from-source-to-target/

public class AllPathsFromSourceToTarget {

  // DFS
  public List<List<Integer>> allPathsSourceTargetDFS(int[][] graph) {
    List<List<Integer>> result = new ArrayList<>();
    if (graph == null || graph.length == 0) {
      return result;
    }

    List<Integer> path = new ArrayList<>();

    path.add(0);
    dfs(graph, 0, result, path);

    return result;
  }

  private void dfs(int[][] graph, int node, List<List<Integer>> result, List<Integer> path) {
    if (node == graph.length - 1) {
      result.add(new ArrayList<>(path));
      return;
    }

    for (int nextNode : graph[node]) {
      path.add(nextNode);
      dfs(graph, nextNode, result, path);
      path.remove(path.size() - 1);
    }
  }

  // BFS
  public List<List<Integer>> allPathsSourceTargetBFS(int[][] graph) {
    List<List<Integer>> result = new ArrayList<>();
    if (graph == null || graph.length == 0) {
      return result;
    }

    Queue<List<Integer>> queue = new LinkedList<>();

    queue.add(Collections.singletonList(0));
    while (!queue.isEmpty()) {
      List<Integer> temp = queue.poll();
      int lastNode = temp.get(temp.size() - 1);
      if (lastNode == graph.length - 1) {
        result.add(temp);
      }
      for (int nextNode : graph[lastNode]) {
        List<Integer> list = new ArrayList<>(temp);
        list.add(nextNode);
        queue.offer(list);
      }
    }

    return result;
  }

  // DFS + Memoization
  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    if (graph == null || graph.length == 0) {
      return Collections.emptyList();
    }

    return dfs(graph, 0, new HashMap<>());
  }

  private List<List<Integer>> dfs(int[][] graph, int node, Map<Integer, List<List<Integer>>> map) {
    if (map.containsKey(node)) {
      return map.get(node);
    }

    List<List<Integer>> result = new ArrayList<>();
    if (node == graph.length - 1) {
      result.add(Collections.singletonList(node));
    } else {
      for (int nextNode : graph[node]) {
        List<List<Integer>> dfs = dfs(graph, nextNode, map);
        for (List<Integer> list : dfs) {
          LinkedList<Integer> newPath = new LinkedList<>(list);
          newPath.addFirst(node);
          result.add(newPath);
        }
      }
    }
    map.put(node, result);

    return result;
  }
}
