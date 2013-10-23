package palabrasamongamigos.resources;

import com.yammer.dropwizard.jersey.params.LongParam;
import org.junit.Test;
import palabrasamongamigos.core.GameModel;
import palabrasamongamigos.core.Move;
import palabrasamongamigos.core.MoveProposal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GamesResourceTest {

    private final MovesResource gamesResource = new MovesResource();

    @Test
    public void getsGameWithCorrectId(){
        GameModel game = gamesResource.getGame(new LongParam("1381180168680"));
        MoveProposal mp = new MoveProposal(1381180168680l, "h,7,^,man", 2, 1);
        Move move = mp.newMove(game);
        move.checkMove();
        assertEquals(1381180168680l,game.getId());
    }
}
