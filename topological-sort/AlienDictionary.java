import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// LC-269
// https://leetcode.com/problems/alien-dictionary/

public class AlienDictionary {

  public String alienOrder(String[] words) {
    if (words == null || words.length == 0) {
      return "";
    }

    Map<Character, Set<Character>> graph = new HashMap<>();
    Map<Character, Integer> inDegrees = new HashMap<>();

    // Initialize the in-degrees to be 0
    for (String word : words) {
      for (char chr : word.toCharArray()) {
        inDegrees.put(chr, 0);
      }
    }

    for (int i = 0; i < words.length - 1; i++) {
      String current = words[i];
      String next = words[i + 1];
      int minLength = Math.min(current.length(), next.length());
      boolean identical = true;
      for (int j = 0; j < minLength; j++) {
        char smaller = current.charAt(j);
        char larger = next.charAt(j);
        if (smaller != larger) {
          identical = false;
          if (!graph.containsKey(smaller)) {
            graph.put(smaller, new HashSet<>());
          }
          Set<Character> neighbors = graph.get(smaller);
          if (!neighbors.contains(larger)) {
            neighbors.add(larger);
            inDegrees.put(larger, inDegrees.get(larger) + 1);
          }
          break;
        }
      }
      if (identical && current.length() > next.length()) {
        return "";
      }
    }

    StringBuilder result = new StringBuilder();

    Queue<Character> queue = new LinkedList<>();
    inDegrees.keySet()
        .stream()
        .filter(c -> inDegrees.get(c) == 0)
        .forEach(queue::offer);

    while (!queue.isEmpty()) {
      Character current = queue.poll();
      result.append(current);
      Set<Character> neighbors = graph.get(current);
      if (neighbors != null) {
        for (Character neighbor : neighbors) {
          int newDegree = inDegrees.get(neighbor) - 1;
          inDegrees.put(neighbor, newDegree);
          if (newDegree == 0) {
            queue.offer(neighbor);
          }
        }
      }
    }

    return result.length() == inDegrees.size() ? result.toString() : "";
  }
}
