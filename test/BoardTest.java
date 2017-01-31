import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardTest {

    @Test
    public void isGameEndTest() {
        Board board = new Board("OXOXOXO  ");
        Assert.assertTrue(board.isGameEnd());
    }

    @Test
    public void isDrawTest() {
        Board board = new Board("OXOXOXOXO");
        Assert.assertTrue(board.isDraw());
    }

    @Test
    public void isPlayer1WinTest() {
        Board board = new Board("XOXOXOX  ");
        Assert.assertTrue(board.isPlayerWin(1));
        board = new Board("XXXO OO  ");
        Assert.assertTrue(board.isPlayerWin(1));
    }

    @Test
    public void isPlayer2WinTest() {
        Board board = new Board("OOOX XX  ");
        Assert.assertTrue(board.isPlayerWin(2));
    }

    @Test
    public void isPlayerNotWinTest() {
        Board board = new Board("OXOXOX   ");
        Assert.assertFalse(board.isPlayerWin(2));
        Assert.assertFalse(board.isPlayerWin(1));
    }

    @Test
    public void boardInitializationTest() {
        Board board = new Board();
        Assert.assertEquals(board.toString(),"         ");
    }

    @Test
    public void addPlayer1MoveTest() {
        Board board = new Board();
        board.addMove(new Move(1, 1), 1);
        Assert.assertEquals(board.toString(), "    X    ");
    }

    @Test
    public void addPlayer2MoveTest() {
        Board board = new Board();
        board.addMove(new Move(0, 0), 2);
        Assert.assertEquals(board.toString(), "O        ");
    }

    @Test
    public void getPossibleMovesTest() {
        Board board = new Board("XOXOXO   ");
        List<Move> expected = new ArrayList<>();
        expected.add(new Move(2, 0));
        expected.add(new Move(2, 1));
        expected.add(new Move(2, 2));
        Assert.assertEquals(board.getPossibleMoves(), expected);
    }

}
