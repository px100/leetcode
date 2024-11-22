
// LC-843
// https://leetcode.com/problems/guess-the-word/

public class GuessTheWord {

//  public void findSecretWord(String[] wordlist, Master master) {
//    Random random = new Random();
//    List<String> words = Arrays.asList(wordlist);
//    for (int i = 0; i < 10; i++) {
//      String testWord = words.get(random.nextInt(words.size()));
//      int score = master.guess(testWord);
//      if (score == 6) {
//        return;
//      }
//      words = words.stream()
//          .filter(w -> charsMatch(w, testWord, score))
//          .collect(Collectors.toList());
//    }
//  }
//
//  private static boolean charsMatch(String w, String testWord, int score) {
//    int matches = 0;
//    for (int i = 0; i < w.length(); i++) {
//      if (w.charAt(i) == testWord.charAt(i)) {
//        matches++;
//      }
//    }
//    return score == matches;
//  }
}
