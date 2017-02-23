import java.util.ArrayList;
import java.util.List;

class Board {

    private char[][] board;
    private List<Move> possibleMoves;

    Board() {
        this.board = new char[3][3];
        this.possibleMoves = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                board[i][j] = ' ';
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    Board(String setup) {
        this.board = new char[3][3];
        this.possibleMoves = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                board[i][j] = setup.charAt(i * 3 + j);
            }
        }
    }


    char[][] getBoard() {
        return this.board;
    }

    List<Move> getPossibleMoves() {
        possibleMoves = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == ' ') {
                    possibleMoves.add(new Move(i, j));
                }
            }
        }
        return possibleMoves;
    }

    boolean isGameEnd() {
        return (isPlayerWin(1) || isPlayerWin(2) || isDraw());
    }

    boolean isPlayerWin(int playerNum) {
        char piece = ' ';
        if (playerNum == 1) {
            piece = 'X';
        } else if (playerNum == 2) {
            piece = 'O';
        }
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == piece)
                || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] == piece)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == piece)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == piece))) {
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

}
