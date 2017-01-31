public class HumanPlayer implements Player{

    private int playerNum;
    private UI ui;

    HumanPlayer(int playerNum, UI ui) {
        this.playerNum = playerNum;
        this.ui = ui;
    }

    @Override
    public void takeMove(Board board) {
        board.addMove(ui.getMove(playerNum, board), playerNum);
    }

}
