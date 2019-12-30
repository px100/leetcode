import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

// LC-210
// https://leetcode.com/problems/course-schedule-ii/

public class CourseScheduleII {

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] result = new int[numCourses];
    int[] inDegree = new int[numCourses];

    for (int[] prerequisite : prerequisites) {
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
      result[count] = course;
      count++;
      for (int[] prerequisite : prerequisites) {
        if (prerequisite[1] == course) {
          if (--inDegree[prerequisite[0]] == 0) {
            queue.add(prerequisite[0]);
          }
        }
      }
    }

    return count == numCourses ? result : new int[0];
  }

  // Time  : O(V+E)
  // Space : O(V)
  public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
    if (numCourses == 0) {
      return null;
    }

    if (prerequisites == null || prerequisites.length == 0) { // no constraint
      return IntStream.range(0, numCourses).toArray();
    }
    // convert to adjacency list
    List<List<Integer>> graph = new ArrayList<>();
    int[] inDegree = new int[numCourses];
    for (int v = 0; v < numCourses; v++) {
      graph.add(new ArrayList<>()); // init
    }

    for (int[] edge : prerequisites) {
      graph.get(edge[1]).add(edge[0]);
      inDegree[edge[0]]++; // count indegree for each node
    }
    // bfs
    int[] result = new int[numCourses];
    int count = 0;
    Queue<Integer> queue = new LinkedList<>();
    for (int v = 0; v < numCourses; v++) {
      if (inDegree[v] == 0) {
        queue.offer(v);
      }
    }

    while (queue.size() > 0) {
      int v = queue.poll();
      result[count++] = v;
      // for each of its neighbors
      for (int w : graph.get(v)) {
        if (--inDegree[w] == 0) {
          queue.offer(w);
        }
      }
    }

    // check if there is topological sort
    return count == numCourses ? result : new int[0];
  }
}
