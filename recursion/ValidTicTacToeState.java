
// LC-794
// https://leetcode.com/problems/valid-tic-tac-toe-state/

public class ValidTicTacToeState {

  private char[][] board = new char[3][3];

  public boolean validTicTacToe(String[] board) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        this.board[i][j] = board[i].charAt(j);
      }
    }

    return validateBoard();
  }

  private boolean validateBoard() {
    int numX = count('X');
    int numO = count('O');

    // X goes first
    if (numO > numX) {
      return false;
    }

    // players take turns
    if (numX > numO + 1) {
      return false;
    }

    // both players can't win
    if (winner('X') && winner('O')) {
      return false;
    }

    // game ends when one player wins
    if (winner('X') && numX == numO) {
      return false;
    }

    // game ends when one player wins
    if (winner('O') && numX > numO) {
      return false;
    }

    return true;
  }

  private int count(char player) {
    int num = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == player) {
          num++;
        }
      }
    }

    return num;
  }

  private boolean winner(char player) {
    if (validateRows(player)) {
      return true;
    }
    if (validateColumns(player)) {
      return true;
    }
    if (validateDiagonal(player)) {
      return true;
    }

    return false;
  }

  private boolean validateRows(char player) {
    for (int i = 0; i < 3; i++) {
      if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
        return true;
      }
    }

    return false;
  }

  private boolean validateColumns(char player) {
    for (int i = 0; i < 3; i++) {
      if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
        return true;
      }
    }

    return false;
  }

  private boolean validateDiagonal(char player) {
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
      return true;
    }
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
      return true;
    }

    return false;
  }
}
