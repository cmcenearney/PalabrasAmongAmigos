package palabrasamongamigos.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MoveErrorsTest {

    private GameModel game = new GameModel();
    Player player1;
    Player player2;

    @Before
    public void setUpTest(){
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        player1 = game.getPlayers().get(0);
        player2 = game.getPlayers().get(1);
        String[] words = {"A", "C", "E", "D", "O", "N", "T"};
        ArrayList<String> playerOneFirstRack = new ArrayList<String>(Arrays.asList(words));
        game.setTileRack(playerOneFirstRack,player1);
        String[] words2 = {"B", "A", "D", "A", "S", "S", "S"};
        ArrayList<String> playerTwoFirstRack = new ArrayList<String>(Arrays.asList(words2));
        game.setTileRack(playerTwoFirstRack,player2);
    }

    @Test
    public void firstMoveMustTouchCenterTile() {
        //Move (GameModel game, int row, int column, String word, boolean across, Player player)
        Move move = new Move(game, 6, 7, "tone", true, player1);
        assertEquals(true, game.validWord("tone"));
        assertEquals(false, move.checkMove());
        assertEquals(Move.firstMoveCenterTile, move.getErrorMessage());
    }

    @Test
    public void testMakeMove(){
        Move move = new Move(game, 7, 7, "tone", true, player1);
        assertEquals(true, move.checkMove());
        move.makeMove();
        assertEquals(8,player1.getScore());
    }
}
