class MoveWithScore {

    private int score;
    private Move move;

    MoveWithScore(int score, Move move) {
        this.score = score;
        this.move = move;
    }

    int getScore() {
        return score;
    }

    Move getMove() {
        return move;
    }
}
