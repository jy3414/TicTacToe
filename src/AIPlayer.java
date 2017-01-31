import java.util.ArrayList;
import java.util.List;

public class AIPlayer implements Player{

    private int playerNum;
    private List<MoveWithScore> possibleScores;

    AIPlayer(int playerNum) {
        this.playerNum = playerNum;
    }

    @Override
    public void takeMove(Board board) {
        minimax(board, playerNum);
        board.addMove(getBestMove(), playerNum);
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

    private Move getBestMove() {
        int max = Integer.MIN_VALUE;
        Move bestMove = new Move();
        for (MoveWithScore move : possibleScores) {
            if (move.getScore() > max) {
                max = move.getScore();
                bestMove = move.getMove();
            }
        }
        return bestMove;
    }

    private void minimax(Board board, int playerNum){
        possibleScores = new ArrayList<>();
        callMinimax(playerNum, board, 0, playerNum);
    }

    private int callMinimax(int playerNum, Board board, int depth, int turn) {
        if (board.isPlayerWin(playerNum))  {
            return 10;
        }
        if (board.isPlayerWin(3 - playerNum)) {
            return -10;
        }
        if (board.isDraw()) {
            return 0;
        }

        List<Move> possibleMoves = board.getPossibleMoves();
        List<Integer> scores = new ArrayList<>();

        for (Move move: possibleMoves) {
            if (turn == playerNum) {
                board.addMove(move, playerNum);
                int currentScore = callMinimax(playerNum, board, depth + 1, 3 - playerNum);
                scores.add(currentScore);
                if (depth == 0)
                    possibleScores.add(new MoveWithScore(currentScore, move));
            } else if (turn == 3 - playerNum) {
                board.addMove(move, 3 - playerNum);
                scores.add(callMinimax(playerNum, board, depth + 1, playerNum));
            }
            board.getBoard()[move.getX()][move.getY()] = ' ';
        }
        return turn == playerNum ? max(scores) : min(scores);
    }
}
