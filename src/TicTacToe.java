import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final int BOARD_SIZE = 3;
    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Board board = new Board();
        System.out.println("Choose game type: ");
        System.out.println("1. Human    vs. Computer");
        System.out.println("2. Human    vs. Human");
        System.out.println("3. Computer vs. Computer");
        int type = input.nextInt();
        if (type == 1) {
            System.out.println("Choose who plays first: ");
            System.out.println("1. You");
            System.out.println("2. Computer");
            if (input.nextInt() == 1) {
                while (!board.isGameEnd()) {
                    boolean isValidMove = false;
                    Move move = new Move();
                    while (!isValidMove) {
                        System.out.println("Please type in your move: [format: x(0 - 2) y(0 - 2)]");
                        move = new Move(input.nextInt(), input.nextInt());
                        if (move.getX() >= 0 && move.getX() <= 2 && move.getY() >= 0 && move.getY() <= 2
                                && board.getBoard()[move.getX()][move.getY()] == ' ') {
                            isValidMove = true;
                        } else {
                            System.out.println("Invalid move! out of bound or not available.");
                        }
                    }
                    board.addMove(move, PLAYER_1);
                    System.out.println("You placed a move: (" + move.getX() + ", " + move.getY() + ").");
                    board.printBoard();
                    if (board.isGameEnd()) {
                        break;
                    }
                    board.minimax(PLAYER_2, PLAYER_2);
                    move = board.getBestMove();
                    board.addMove(move, PLAYER_2);
                    System.out.println("Computer placed a move: (" + move.getX() + ", " + move.getY() + ").");
                    board.printBoard();
                }
                if (board.isPlayer1Win()) {
                    System.out.println("Victory!");
                } else if (board.isPlayer2Win()) {
                    System.out.println("Defeat!");
                } else if (board.isDraw()) {
                    System.out.println("Draw!");
                }
            } else {
                Random rand = new Random();
                Move move = new Move(rand.nextInt(BOARD_SIZE), rand.nextInt(BOARD_SIZE));
                board.addMove(move, PLAYER_1);
                System.out.println("Computer placed a move: (" + move.getX() + ", " + move.getY() + ").");
                board.printBoard();
                while (!board.isGameEnd()) {
                    boolean isValidMove = false;
                    while (!isValidMove) {
                        System.out.println("Please type in your move: [format: x(0 - 2) y(0 - 2)]");
                        move = new Move(input.nextInt(), input.nextInt());
                        if (move.getX() >= 0 && move.getX() <= 2 && move.getY() >= 0 && move.getY() <= 2
                                && board.getBoard()[move.getX()][move.getY()] == ' ') {
                            isValidMove = true;
                        } else {
                            System.out.println("Invalid move! out of bound or not available.");
                        }
                    }
                    board.addMove(move, PLAYER_2);
                    System.out.println("You placed a move: (" + move.getX() + ", " + move.getY() + ").");
                    board.printBoard();
                    if (board.isGameEnd()) {
                        break;
                    }
                    board.minimax(PLAYER_1, PLAYER_1);
                    move = board.getBestMove();
                    board.addMove(move, PLAYER_1);
                    System.out.println("Computer placed a move: (" + move.getX() + ", " + move.getY() + ").");
                    board.printBoard();
                }
                if (board.isPlayer1Win()) {
                    System.out.println("Defeat!");
                } else if (board.isPlayer2Win()) {
                    System.out.println("Victory!");
                } else if (board.isDraw()){
                    System.out.println("Draw!");
                }
            }
        } else if (type == 2) {
            int currPlayer = PLAYER_1;
            while (!board.isGameEnd()) {
                boolean isValidMove = false;
                Move move = new Move();
                while (!isValidMove) {
                    System.out.println("Please type in Player " + currPlayer + "'s move: ");
                    move = new Move(input.nextInt(), input.nextInt());
                    if (move.getX() >= 0 && move.getX() <= 2 && move.getY() >= 0 && move.getY() <= 2
                            && board.getBoard()[move.getX()][move.getY()] == ' ') {
                        isValidMove = true;
                    } else {
                        System.out.println("Invalid move! out of bound or not available.");
                    }
                }
                board.addMove(move, currPlayer);
                System.out.println("Player " + currPlayer + " placed a move: (" + move.getX()
                        + ", " + move.getY() + ").");
                if (currPlayer == PLAYER_1) {
                    currPlayer = PLAYER_2;
                } else {
                    currPlayer = PLAYER_1;
                }
                board.printBoard();
                if (board.isGameEnd()) {
                    break;
                }
            }
            if (board.isPlayer1Win()) {
                System.out.println("Player 1 has won!");
            } else if (board.isPlayer2Win()) {
                System.out.println("Player 2 has won!");
            } else if (board.isDraw()) {
                System.out.println("Draw!");
            }
        } else if (type == 3) {
            Random rand = new Random();
            Move move = new Move(rand.nextInt(BOARD_SIZE), rand.nextInt(BOARD_SIZE));
            board.addMove(move, PLAYER_1);
            System.out.println("Computer 1 placed a move: (" + move.getX() + ", " + move.getY() + ").");
            board.printBoard();
            while (!board.isGameEnd()) {
                board.minimax(PLAYER_2, PLAYER_2);
                move = board.getBestMove();
                board.addMove(move, PLAYER_2);
                System.out.println("Computer 2 placed a move: (" + move.getX() + ", " + move.getY() + ").");
                board.printBoard();
                if (board.isGameEnd()) {
                    break;
                }
                board.minimax(PLAYER_1, PLAYER_1);
                move = board.getBestMove();
                board.addMove(move, PLAYER_1);
                System.out.println("Computer 1 placed a move: " + "(" + move.getX() + ", " + move.getY() + ").");
                board.printBoard();
                if (board.isGameEnd()) {
                    break;
                }
            }
            if (board.isPlayer1Win()) {
                System.out.println("Computer 1 has won!");
            } else if (board.isPlayer2Win()) {
                System.out.println("Computer 2 has won!");
            } else if (board.isDraw()){
                System.out.println("Draw!");
            }
        } else {
            System.out.println("Invalid game type!");
        }
    }
}
