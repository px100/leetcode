
// LC-779
// https://leetcode.com/problems/k-th-symbol-in-grammar/

public class KthSymbolInGrammar {

  public int kthGrammar(int N, int K) {
    return Integer.bitCount(K - 1) % 2 == 0 ? 0 : 1;
  }

  public int kthGrammarRec(int N, int K) {
    if (N == 1) {
      return 0;
    }

    if (K % 2 == 0) {
      return kthGrammarRec(N - 1, K / 2) == 0 ? 1 : 0;
    } else {
      return kthGrammarRec(N - 1, (K + 1) / 2) == 0 ? 0 : 1;
    }
  }
}
