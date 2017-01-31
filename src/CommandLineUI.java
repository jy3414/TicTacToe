import java.util.Scanner;

public class CommandLineUI implements UI{

    private Scanner input;

    CommandLineUI() {
        this.input = new Scanner(System.in);
    }

    @Override
    public int getGameMode() {
        int mode = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("Choose game type: ");
            System.out.println("1. Human    vs. Computer");
            System.out.println("2. Human    vs. Human");
            System.out.println("3. Computer vs. Computer");
            mode = input.nextInt();
            if (mode == 1 || mode == 2 || mode == 3) {
                isValidInput = true;
            }
        }
        return mode;
    }

    @Override
    public int getPlayerTurn() {
        int turn = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("Choose who plays first: ");
            System.out.println("1. You");
            System.out.println("2. Computer");
            turn = input.nextInt();
            if (turn == 1 || turn == 2) {
                isValidInput = true;
            }
        }
        return turn;
    }

    @Override
    public Move getMove(int playerNum, Board board) {
        Move move = new Move(-1, -1);
        boolean isValidMove = false;
        while (!isValidMove) {
            System.out.println("Please type in Player " + playerNum + "'s move: ");
            move = new Move(input.nextInt(), input.nextInt());
            if (move.getX() >= 0 && move.getX() <= 2 && move.getY() >= 0 && move.getY() <= 2
                    && board.getBoard()[move.getX()][move.getY()] == ' ') {
                isValidMove = true;
            } else {
                System.out.println("Invalid move! out of bound or not available.");
            }
        }
        return move;
    }

    @Override
    public void endGameMessage(Board board) {
        if (board.isPlayerWin(1)) {
            System.out.println("Player 1 has won!");
        } else if (board.isPlayerWin(2)) {
            System.out.println("Player 2 has won!");
        } else if (board.isDraw()) {
            System.out.println("Draw!");
        }
    }

    @Override
    public void showBoard(Board board) {
        System.out.println("  0   1   2");
        for (int i = 0; i < 3; ++i) {
            System.out.print(i);
            for (int j = 0; j < 3; ++j) {
                System.out.print(" " + board.getBoard()[i][j] + " ");
                if (j != 3 - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i != 2) {
                System.out.println(" ---+---+---");
            }
        }
        System.out.println();
        System.out.println();
    }
}
