import org.junit.Assert;
import org.junit.Test;
public class AIPlayerTest {

    @Test
    public void takeWinningMoveTest() {
         Board board = new Board("X XOO    ");
         Player player = new AIPlayer(1);
         player.takeMove(board);
         Assert.assertEquals(board.toString(), "XXXOO    ");
    }

    @Test
    public void takeDefendingMoveTest() {
        Board board = new Board("X XOO    ");
        Player player = new AIPlayer(2);
        player.takeMove(board);
        Assert.assertEquals(board.toString(), "XOXOO    ");
    }

}
