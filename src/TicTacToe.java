public class TicTacToe {

    public static void main(String[] args) {
        Player p1 = new AIPlayer(1);
        Player p2 = new AIPlayer(2);
        UI ui = new CommandLineUI();
        int gameMode = ui.getGameMode();
        if (gameMode == 1) {
            int turn = ui.getPlayerTurn();
            if (turn == 1) {
                p1 = new HumanPlayer(1, ui);
            } else if (turn == 2) {
                p2 = new HumanPlayer(2, ui);
            }
        } else if (gameMode == 2) {
            p1 = new HumanPlayer(1, ui);
            p2 = new HumanPlayer(2, ui);
        }
        startGame(ui, p1, p2);
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
