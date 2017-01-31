public interface UI {

    int getGameMode();

    int getPlayerTurn();

    Move getMove(int playerNum, Board board);

    void endGameMessage(Board board);

    void showBoard(Board board);
}
