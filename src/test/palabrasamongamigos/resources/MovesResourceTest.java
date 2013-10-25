package palabrasamongamigos.resources;

import com.yammer.dropwizard.jersey.params.LongParam;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import palabrasamongamigos.DatabaseAccessor;
import palabrasamongamigos.core.GameModel;
import palabrasamongamigos.core.MoveProposal;
import palabrasamongamigos.core.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MovesResourceTest {

    MovesResource movesResource = new MovesResource();
    private GameModel game = new GameModel();
    Player player1;
    Player player2;
    private final DatabaseAccessor db = DatabaseAccessor.INSTANCE;

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
        db.saveGame(game);
    }

    @Test
    public void makeMoveWorks(){
        LongParam idParam = new LongParam(String.valueOf(game.getId()));
        MoveProposal moveProposal = new MoveProposal(game.getId(), "h,6,>,tone", 1,0);
        GameModel updatedGame = movesResource.makeMove(idParam, moveProposal);
        assertEquals("T", updatedGame.getBoard().getSpace(7,5).getValue());
    }

    @After
    public void cleanUp(){
        db.deleteGame(game);
    }
}
     /*
class to handle incoming json move requests, or "proposals", eg:
  {
    "id": 123456789,
    "moveString": "a,12,>,word",
    "moveNumber": 2,
    "currentTurn": 0
  }
*/