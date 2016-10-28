/**
 *
 * @author Arshad
 */
import java.util.Scanner;
public class NQueens {

    int[] board;
    private static int count = 0;

    public NQueens(int N) {
        board = new int[N];
    }

    public boolean canPlaceQueen(int r, int c) {
        /**
         * Returns TRUE if a queen can be placed in row r and column c.
         * Otherwise it returns FALSE. x[] is a global array whose first (r-1)
         * values have been set.
         */
        for (int i = 0; i < r; i++) {
            if (board[i] == c || (i - r) == (board[i] - c) ||(i - r) == (c - board[i])) 
            {
                return false;
            }
        }
        return true;
    }

    public void print(int[] board) {
        System.out.println("Solution " + (++count));
        int N = board.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i] == j) {
                    System.out.print("|Q");
                } else {
                    System.out.print("|_");
                }
            }
            System.out.println("|");
        }
        System.out.println();
    }

    public void place(int r, int n) {
        /**
         * Using backtracking this method prints all possible placements of n
         * queens on an n x n chessboard so that they are non-attacking.
         */
        for (int c = 0; c < n; c++) {
            if (canPlaceQueen(r, c)) {
                board[r] = c;
                if (r == n - 1) {
                    print(board);
                } else {
                    place(r + 1, n);
                }
            }
        }
    }

    public void call() {
        place(0, board.length);
    }

    public static void main(String args[]) {
        Scanner rex = new Scanner(System.in);
        System.out.println("How many Queens?");
        int n = rex.nextInt();
        NQueens Q = new NQueens(n);
        Q.call();
     
    }
}