import java.util.ArrayList;
import java.util.List;

public class WordSearchII {

  public List<String> findWords(char[][] board, String[] words) {
    int colCount = board[0].length;

    int boardBitMask = 0;
    for (int row = board.length - 1; row >= 0; row--) {
      for (int col = colCount - 1; col >= 0; col--) {
        boardBitMask |= 1 << (board[row][col] - 'a');
      }
    }

    List<String> result = new ArrayList<>();
    for (int wordsIdx = words.length - 1; wordsIdx >= 0; wordsIdx--) {
      if (findOneWord(words[wordsIdx], boardBitMask, board, colCount)) {
        result.add(words[wordsIdx]);
      }
    }

    return result;
  }

  private boolean findOneWord(String word, int boardBitMask, char[][] board, int colCount) {
    char[] wordC = word.toCharArray();
    char lastChar = wordC[wordC.length - 1];

    if (wordC.length > board.length * colCount) {
      return false;
    }

    for (int i = wordC.length - 1; i >= 0; i--) {
      if ((boardBitMask & (1 << (wordC[i] - 'a'))) == 0) {
        return false;
      }
    }

    for (int row = board.length - 1; row >= 0; row--) {
      for (int col = colCount - 1; col >= 0; col--) {
        if (board[row][col] == lastChar) {
          if (dfs(row, col, wordC.length - 1, wordC, board)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  private boolean dfs(int row, int col, int wordIdx, char[] wordC, char[][] board) {
    if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
      return false;
    }
    if (board[row][col] != wordC[wordIdx]) {
      return false;
    }
    if (wordIdx <= 0) {
      return true;
    }
    board[row][col] ^= 128;
    boolean searchResult = dfs(row - 1, col, wordIdx - 1, wordC, board)
        || dfs(row, col - 1, wordIdx - 1, wordC, board)
        || dfs(row + 1, col, wordIdx - 1, wordC, board)
        || dfs(row, col + 1, wordIdx - 1, wordC, board);
    board[row][col] ^= 128;
    return searchResult;
  }
}
