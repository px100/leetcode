import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// LC-126
// https://leetcode.com/problems/word-ladder-ii/

public class WordLadderII {

  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> result = new ArrayList<>();
    Set<String> words = new HashSet<>(wordList);
    if (!words.contains(endWord)) {
      return result;
    }

    Set<String> visited = new HashSet<>();
    PriorityQueue<List<String>> pq = new PriorityQueue<>(Comparator.comparingInt(List::size));

    List<String> list = new ArrayList<>();
    list.add(beginWord);
    pq.offer(list);
    int level = 1;
    int min = Integer.MAX_VALUE;
    while (!pq.isEmpty()) {
      level++;
      int size = pq.size();
      for (int k = 0; k < size; k++) {
        List<String> l1 = pq.poll();
        if (level > min) {
          return result;
        }
        if (l1.get(l1.size() - 1).equals(endWord)) {
          result.add(l1);
          min = Math.min(min, level);
        } else {
          String last = l1.get(l1.size() - 1);
          visited.add(last);
          for (int i = 0; i < last.length(); i++) {
            char[] chr = last.toCharArray();
            for (int j = 0; j < 26; j++) {
              chr[i] = (char) ('a' + j);
              String str = String.valueOf(chr);
              if (words.contains(str) && !visited.contains(str)) {
                List<String> l2 = new ArrayList<>(l1);
                l2.add(str);
                pq.offer(l2);
              }
            }
          }
        }
      }
    }

    return result;
  }

  public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> result = new ArrayList<>();
    Set<String> words = new HashSet<>(wordList);
    if (!words.contains(endWord)) {
      return result;
    }

    Set<String> beginSet = new HashSet<>();
    Set<String> endSet = new HashSet<>();
    beginSet.add(beginWord);
    endSet.add(endWord);

    Map<String, List<String>> map = new HashMap<>(); // beginWord -> nextWord
    helper(words, beginSet, endSet, map, false); // build the map

    List<String> path = new ArrayList<>();
    path.add(beginWord);
    getAllPath(beginWord, endWord, map, path, result);

    return result;
  }

  private boolean helper(Set<String> dict, Set<String> beginSet, Set<String> endSet,
    Map<String, List<String>> map, boolean flip) {
    if (beginSet.isEmpty()) {
      return false;
    }
    if (beginSet.size() > endSet.size()) {
      return helper(dict, endSet, beginSet, map, !flip); // always expand the smaller queue first
    }

    dict.removeAll(beginSet); // remove words on current both ends from the dict
    dict.removeAll(endSet);
    boolean done = false;  // as we only need the shortest paths, use a boolean value help early termination
    Set<String> visited = new HashSet<>(); // set for the next level
    for (String word : beginSet) {
      for (int i = 0; i < word.length(); i++) {
        char[] chr = word.toCharArray();
        for (char c = 'a'; c <= 'z'; c++) {
          chr[i] = c;
          String s = new String(chr);
          // make sure we construct the tree in the correct direction
          String key = flip ? s : word;
          String val = flip ? word : s;
          map.putIfAbsent(key, new ArrayList<>());
          if (endSet.contains(s)) {
            done = true;
            map.get(key).add(val);
          }
          if (!done && dict.contains(s)) {
            visited.add(s);
            map.get(key).add(val);
          }
        }
      }
    }
    // terminate if done is true
    return done || helper(dict, endSet, visited, map, !flip);
  }

  private void getAllPath(String beginWord, String endWord, Map<String, List<String>> map,
    List<String> path, List<List<String>> result) {
    if (beginWord.equals(endWord)) {
      result.add(new ArrayList<>(path));
      return;
    }
    // check in case the diff between start and end happens to be one:
    // e.g "a", "c", {"a", "b", "c"}
    if (!map.containsKey(beginWord)) {
      return;
    }

    for (String word : map.get(beginWord)) {
      path.add(word);
      getAllPath(word, endWord, map, path, result);
      path.remove(path.size() - 1);
    }
  }
}
