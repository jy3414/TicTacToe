public class TicTacToe {

    public static void main(String[] args) {
        UI ui = new CommandLineUI();
        int gameMode = ui.getGameMode();
        if (gameMode == 1) {
            int turn = ui.getPlayerTurn();
            if (turn == 1) {
                HumanPlayer p1 = new HumanPlayer(1, ui);
                AIPlayer p2 = new AIPlayer(2);
                startGame(ui, p1, p2);
            } else if (turn == 2) {
                AIPlayer p1 = new AIPlayer(1);
                HumanPlayer p2 = new HumanPlayer(2, ui);
                startGame(ui, p1, p2);
            }
        } else if (gameMode == 2) {
            HumanPlayer p1 = new HumanPlayer(1, ui);
            HumanPlayer p2 = new HumanPlayer(2, ui);
            startGame(ui, p1, p2);
        } else if (gameMode == 3) {
            AIPlayer p1 = new AIPlayer(1);
            AIPlayer p2 = new AIPlayer(2);
            startGame(ui, p1, p2);
        }
    }

    private static void startGame(UI ui, Player p1, Player p2) {
        Board board = new Board();
        ui.showBoard(board);
        while (!board.isGameEnd()) {
            p1.takeMove(board);
            ui.showBoard(board);
            if (board.isGameEnd()) {
                ui.endGameMessage(board);
                break;
            }
            p2.takeMove(board);
            ui.showBoard(board);
            if (board.isGameEnd()) {
                ui.endGameMessage(board);
                break;
            }
        }
    }
}
