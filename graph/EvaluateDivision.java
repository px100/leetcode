import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC-399
// https://leetcode.com/problems/evaluate-division/

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
}
