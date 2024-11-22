import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// LC-1065
// https://leetcode.com/problems/index-pairs-of-a-string

// Given a text string and words (a list of strings),
// return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.

// Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
// Output: [[3,7],[9,13],[10,17]]

// Input: text = "ababa", words = ["aba","ab"]
// Output: [[0,1],[0,2],[2,3],[2,4]]
// Explanation:
// Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].

public class IndexPairsOfAString {

  private static class Trie {

    boolean isWord;
    Trie[] children;

    public Trie() {
      isWord = false;
      children = new Trie[26];
    }
  }

  private void insert(String word, Trie root) {
    Trie cur = root;
    for (char c : word.toCharArray()) {
      if (cur.children[c - 'a'] == null) {
        cur.children[c - 'a'] = new Trie();
      }
      cur = cur.children[c - 'a'];
    }
    cur.isWord = true;
  }

  // TC: O(M^2 + sum(len(word))), M is length of text
  // SC: O(26^max(len(words)))
  public int[][] indexPairs(String text, String[] words) {
    if (text == null || text.length() == 0) {
      return new int[0][2];
    }

    Trie root = new Trie();
    for (String word : words) {
      insert(word, root);
    }
    List<int[]> list = new ArrayList<>();
    for (int i = 0; i < text.length(); i++) {
      Trie cur = root;
      for (int j = i; j < text.length(); j++) {
        int num = text.charAt(j) - 'a';
        if (cur.children[num] == null) {
          break;
        } else {
          cur = cur.children[num];
          if (cur.isWord) {
            list.add(new int[]{i, j});
          }
        }
      }
    }
    return list.toArray(new int[list.size()][2]);
  }

  public int[][] indexPairs2(String text, String[] words) {
    List<int[]> list = new ArrayList<>();
    for (String word : words) {
      int firstIndex = 0;
      while (text.indexOf(word, firstIndex) != -1) {
        int start = text.indexOf(word, firstIndex);
        list.add(new int[]{start, start + word.length() - 1});
        firstIndex = start + 1;
      }
    }
    list.sort((p1, p2) -> (p1[0] == p2[0]) ? p1[1] - p2[1] : p1[0] - p2[0]);
    return list.toArray(new int[list.size()][2]);
  }

  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, (p1, p2) -> p1[0] != p2[0]
        ? Integer.compare(p2[0], p1[0])
        : Integer.compare(p1[1], p2[1]));
    List<int[]> list = new ArrayList<>();
    Arrays.stream(people).forEach(p -> list.add(p[1], p));
    return list.toArray(new int[people.length][]);
  }
}
