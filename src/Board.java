import java.util.ArrayList;
import java.util.List;

class Board {

    private static final int BOARD_SIZE = 3;
    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;

    private char[][] board;
    private List<Move> possibleMoves;
    private List<MoveWithScore> possibleScores;

    Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.possibleMoves = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                board[i][j] = ' ';
            }
        }
    }

    char[][] getBoard() {
        return this.board;
    }

    void printBoard() {
        System.out.println("  0   1   2");
        for (int i = 0; i < BOARD_SIZE; ++i) {
            System.out.print(i);
            for (int j = 0; j < BOARD_SIZE; ++j) {
                System.out.print(" " + board[i][j] + " ");
                if (j != BOARD_SIZE - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i != BOARD_SIZE - 1) {
                System.out.println(" ---+---+---");
            }
        }
        System.out.println();
        System.out.println();
    }

    /* Get current available moves */
    private List<Move> getPossibleMoves() {
        possibleMoves = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == ' ') {
                    possibleMoves.add(new Move(i, j));
                }
            }
        }
        return possibleMoves;
    }

    boolean isGameEnd() {
        return (isPlayer1Win() || isPlayer2Win() || isDraw());
    }

    boolean isPlayer1Win() {
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == 'X')
                || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] == 'X')) {
            return true;
        }
        for (int i = 0; i < BOARD_SIZE; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 'X')
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 'X'))) {
                return true;
            }
        }
        return false;
    }

    boolean isPlayer2Win() {
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == 'O')
                || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] == 'O')) {
            return true;
        }
        for (int i = 0; i < BOARD_SIZE; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 'O')
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 'O')) {
                return true;
            }
        }
        return false;
    }

    boolean isDraw() {
        return (getPossibleMoves().isEmpty());
    }

    void addMove(Move move, int player) {
        if (player == 1) {
            board[move.getX()][move.getY()] = 'X';
        } else {
            board[move.getX()][move.getY()] = 'O';
        }
    }

    /* Get move with highest score from all the possible scores */
    Move getBestMove() {
        int max = Integer.MIN_VALUE;
        Move bestMove = new Move(-1, -1);
        for (MoveWithScore move : possibleScores) {
            if (move.getScore() > max) {
                max = move.getScore();
                bestMove = move.getMove();
            }
        }
        return bestMove;
    }

    private int min(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    private int max(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    /* Auxiliary function, call minimax for each player respectively */
    void minimax(int turn, int player){
        possibleScores = new ArrayList<>();
        if (player == PLAYER_1) {
            minimax_Player1(0, turn);
        } else {
            minimax_Player2(0, turn);
        }

    }

    /* Minimax for player 1, add all future possible scores into possibleScores
       assuming the opponent always makes the best move */
    private int minimax_Player1(int depth, int turn) {

        if (isPlayer1Win())  {
            return 10;
        }
        if (isPlayer2Win()) {
            return -10;
        }
        if (isDraw()) {
            return 0;
        }

        List<Move> possibleMoves = getPossibleMoves();
        List<Integer> scores = new ArrayList<>();

        for (Move move: possibleMoves) {
            if (turn == PLAYER_1) {
                addMove(move, PLAYER_1);
                int currentScore = minimax_Player1(depth + 1, PLAYER_2);
                scores.add(currentScore);
                if (depth == 0)
                    possibleScores.add(new MoveWithScore(currentScore, move));
            } else if (turn == PLAYER_2) {
                addMove(move, PLAYER_2);
                scores.add(minimax_Player1(depth + 1, PLAYER_1));
            }
            board[move.getX()][move.getY()] = ' ';
        }
        return turn == 1 ? max(scores) : min(scores);
    }

    /* Minimax for player 2, add all future possible scores into possibleScores
           assuming the opponent always makes the best move */
    private int minimax_Player2(int depth, int turn) {

        if (isPlayer1Win())  {
            return -10;
        }
        if (isPlayer2Win()) {
            return 10;
        }
        if (isDraw()) {
            return 0;
        }

        List<Move> possibleMoves = getPossibleMoves();
        List<Integer> scores = new ArrayList<>();

        for (Move move: possibleMoves) {
            if (turn == PLAYER_2) {
                addMove(move, PLAYER_2);
                int currentScore = minimax_Player2(depth + 1, PLAYER_1);
                scores.add(currentScore);
                if (depth == 0)
                    possibleScores.add(new MoveWithScore(currentScore, move));
            } else if (turn == PLAYER_1) {
                addMove(move, PLAYER_1);
                scores.add(minimax_Player2(depth + 1, PLAYER_2));
            }
            board[move.getX()][move.getY()] = ' ';
        }
        return turn == 2 ? max(scores) : min(scores);
    }
}
