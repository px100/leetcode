import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// LC-399
// https://leetcode.com/problems/evaluate-division/

//  You are given an array of variable pairs equations and an array of real numbers values,
//  where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
//  Each Ai or Bi is a string that represents a single variable.
//
//  You are also given some queries, where queries[j] = [Cj, Dj]
//  represents the jth query where you must find the answer for Cj / Dj = ?.
//
//  Return the answers to all queries. If a single answer cannot be determined, return -1.0.
//
//  Note: The input is always valid.
//  You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
//
//  Example 1:
//
//  Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//  Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
//  Explanation:
//  Given: a / b = 2.0, b / c = 3.0
//  queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//  return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
//
//  Example 2:
//
//  Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//  Output: [3.75000,0.40000,5.00000,0.20000]
//
//  Example 3:
//
//  Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//  Output: [0.50000,2.00000,-1.00000,-1.00000]
//
//  Constraints:
//
//  1 <= equations.length <= 20
//  equations[i].length == 2
//  1 <= Ai.length, Bi.length <= 5
//  values.length == equations.length
//  0.0 < values[i] <= 20.0
//  1 <= queries.length <= 20
//  queries[i].length == 2
//  1 <= Cj.length, Dj.length <= 5
//  Ai, Bi, Cj, Dj consist of lower case English letters and digits.

public class EvaluateDivision {

  // multi-source shortest path
  public double[] calcEquation(
      List<List<String>> equations, double[] values, List<List<String>> queries) {
    int nodes = 0;
    Map<String, Integer> indexMap = new HashMap<>();
    for (List<String> equation : equations) {
      indexMap.putIfAbsent(equation.get(0), nodes++);
      indexMap.putIfAbsent(equation.get(1), nodes++);
    }

    double[][] dist = new double[nodes][nodes];
    for (int i = 0; i < equations.size(); i++) {
      int from = indexMap.get(equations.get(i).get(0));
      int to = indexMap.get(equations.get(i).get(1));
      double weight = values[i];
      dist[from][to] = weight;
      dist[to][from] = 1.0 / weight;
    }

    for (int k = 0; k < nodes; k++) {
      for (int i = 0; i < nodes; i++) {
        for (int j = 0; j < nodes; j++) {
          if (dist[i][k] > 0 && dist[k][j] > 0) {
            dist[i][j] = dist[i][k] * dist[k][j];
          }
        }
      }
    }

    double[] result = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      Integer from = indexMap.get(queries.get(i).get(0));
      Integer to = indexMap.get(queries.get(i).get(1));
      result[i] = from == null || to == null ? -1 : dist[from][to] > 0 ? dist[from][to] : -1;
    }

    return result;
  }

  // DFS
  public double[] calcEquationfs(
      List<List<String>> equations, double[] values, List<List<String>> queries) {
    Map<String, Map<String, Double>> map = new HashMap<>();
    for (int i = 0; i < values.length; i++) {
      map.putIfAbsent(equations.get(i).get(0), new HashMap<>());
      map.putIfAbsent(equations.get(i).get(1), new HashMap<>());
      map.get(equations.get(i).get(0)).put(equations.get(i).get(1), values[i]);
      map.get(equations.get(i).get(1)).put(equations.get(i).get(0), 1 / values[i]);
    }
    double[] r = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      r[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), 1, map, new HashSet<>());
    }
    return r;
  }

  private double dfs(
      String start, String end, double value,
      Map<String, Map<String, Double>> map, Set<String> seen) {
    if (!map.containsKey(start) || !seen.add(start)) {
      return -1;
    }
    if (start.equals(end)) {
      return value;
    }
    Map<String, Double> next = map.get(start);
    for (String c : next.keySet()) {
      double result = dfs(c, end, value * next.get(c), map, seen);
      if (result != -1) {
        return result;
      }
    }
    return -1;
  }

  // BFS
  public double[] calcEquationBfs(
      List<List<String>> equations, double[] values, List<List<String>> queries) {
    int j = 0;
    Map<String, Integer> map = new HashMap<>();
    for (List<String> equation : equations) {
      for (String s : equation) {
        map.putIfAbsent(s, j++);
      }
    }
    double[][] graph = new double[j][j];
    for (int i = 0; i < equations.size(); i++) {
      List<String> l = equations.get(i);
      int x = map.get(l.get(0));
      int y = map.get(l.get(1));
      graph[x][y] = values[i];
      graph[y][x] = 1 / values[i];
    }

    double[] ans = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      int x = map.getOrDefault(queries.get(i).get(0), -1);
      int y = map.getOrDefault(queries.get(i).get(1), -1);
      ans[i] = x == -1 || y == -1 ? -1 : bfs(graph, x, y);
    }
    return ans;
  }

  private double bfs(double[][] graph, int start, int end) {
    int graphSize = graph.length;
    double[] valueStart = new double[graphSize];
    valueStart[start] = 1;
    boolean[] visited = new boolean[graphSize];
    visited[start] = true;
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    while (!queue.isEmpty()) {
      int t = queue.poll();
      if (t == end) {
        return valueStart[t];
      }
      for (int i = 0; i < graphSize; i++) {
        if (!visited[i] && graph[t][i] != 0) {
          valueStart[i] = valueStart[t] * graph[t][i];
          queue.offer(i);
          visited[i] = true;
        }
      }
    }
    return -1;
  }
}
