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
        if (playerNum == 1) {
            if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == 'X')
                    || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] == 'X')) {
                return true;
            }
            for (int i = 0; i < 3; ++i) {
                if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 'X')
                        || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 'X'))) {
                    return true;
                }
            }
            return false;
        } else if (playerNum == 2) {
            if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == 'O')
                    || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] == 'O')) {
                return true;
            }
            for (int i = 0; i < 3; ++i) {
                if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 'O')
                        || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 'O')) {
                    return true;
                }
            }
            return false;
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
