package palabrasamongamigos.resources;

import com.yammer.dropwizard.jersey.params.LongParam;
import palabrasamongamigos.DatabaseAccessor;
import palabrasamongamigos.core.GameModel;
import palabrasamongamigos.core.Move;
import palabrasamongamigos.core.MoveProposal;
import palabrasamongamigos.core.PalabrasModel;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/game/{id}/move")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovesResource {

    private final DatabaseAccessor db = DatabaseAccessor.INSTANCE;

    public MovesResource() {}

    @POST
    public GameModel makeMove(@PathParam("id") LongParam gameId, @Valid MoveProposal moveProposal) {
        //doing this because instantiation default upper-cases moveString - the json data remains "virtual", no instantiation
        MoveProposal moveProObject = new MoveProposal(moveProposal.getId(),moveProposal.getMoveString(),moveProposal.getMoveNumber(),moveProposal.getCurrentTurn());

        GameModel game = db.getGameById(moveProObject.getId());

        //TODO: these are application errors and the http response should have appropriate code
        // error message should be part of response, not game object
        if (gameId.get() != moveProposal.getId()) {
            game.setErrorMsg("ERROR - url ID " + gameId.get() + "does not ID in json data " + moveProposal.getId() );
            return game;
        }
        if (moveProposal.getCurrentTurn() != game.getCurrentTurn()) {
            game.setErrorMsg("ERROR - currentTurn does not match currentTurn for game id=" + game.getId());
            return game;
        }
        if (moveProposal.getMoveNumber() != game.getMoves().size()+1){
            game.setErrorMsg("ERROR - moveNumber does not match number of moves for game id=" + game.getId());
            return game;
        }

        Move move = moveProObject.newMove(game);
        if (move.checkMove()){
            move.makeMove();
        } else {
            game.setErrorMsg(move.getErrorMessage());
        }
        db.save(game);
        return game;
    }

}


