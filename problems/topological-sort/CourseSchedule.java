import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// LC-207
// https://leetcode.com/problems/course-schedule/

public class CourseSchedule {

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites == null) {
      return true;
    }

    Map<Integer, List<Integer>> adjMap = new HashMap<>();
    int[] inDegree = new int[numCourses];

    for (int[] prerequisite : prerequisites) {
      adjMap.putIfAbsent(prerequisite[1], new ArrayList<>());
      adjMap.get(prerequisite[1]).add(prerequisite[0]);
      inDegree[prerequisite[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }

    int count = 0;
    while (!queue.isEmpty()) {
      int course = queue.remove();
      count++;
      if (adjMap.containsKey(course)) {
        for (int child : adjMap.get(course)) {
          if (--inDegree[child] == 0) {
            queue.add(child);
          }
        }
      }
    }

    return count == numCourses;
  }

  enum Status {
    NOT_VISITED, VISITED, VISITING;
  }

  public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
    if (prerequisites == null) {
      return true;
    }
    // build graph
    List<List<Integer>> list = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      list.add(new ArrayList<>());
    }

    for (int[] p : prerequisites) {
      int prerequisite = p[1];
      int course = p[0];
      list.get(course).add(prerequisite);
    }

    Status[] visited = new Status[numCourses];
    for (int i = 0; i < numCourses; i++) {
      // if there is a cycle, return false
      if (dfs(list, visited, i)) {
        return false;
      }
    }

    return true;
  }

  private boolean dfs(List<List<Integer>> list, Status[] visited, int cur) {
    if (visited[cur] == Status.VISITING) {
      return true;
    }
    if (visited[cur] == Status.VISITED) {
      return false;
    }

    visited[cur] = Status.VISITING;
    for (int next : list.get(cur)) {
      if (dfs(list, visited, next)) {
        return true;
      }
    }
    visited[cur] = Status.VISITED;

    return false;
  }
}
