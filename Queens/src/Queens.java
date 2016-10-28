/**
 *
 * @author Arshad
 */
public class Queens {
 
  private static int[] board = new int[8];
  private static int count = 0;
 
  static boolean overlap(int y) {
    int x = board[y];
    for (int i = 1; i <= y; i++) {
      int t = board[y - i];
      if (t == x || t == x - i || t == x + i) {
        return true;
      }
    }
 
    return false;
  }
 
  public static void put() {
    System.out.println("Solution " + (++count));
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        System.out.print((board[y] == x) ? "|Q" : "|_");
      }
      System.out.println("|");
    }
      System.out.println("");
  }
 
  public static void main(String[] args) {
    int y = 0;
    board[0] = -1;
    while (y >= 0) {
      do {
        board[y]++;
      } while ((board[y] < 8) && overlap(y));
      if (board[y] < 8) {
        if (y < 7) {
          board[++y] = -1;
        } else {
          put();
        }
      } else {
        y--;
      }
    }
  }
}