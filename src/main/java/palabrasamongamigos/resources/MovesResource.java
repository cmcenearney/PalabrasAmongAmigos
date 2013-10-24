package palabrasamongamigos.resources;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.util.JSON;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import palabrasamongamigos.DatabaseAccessor;
import palabrasamongamigos.MongoResource;
import palabrasamongamigos.core.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

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

        GameModel game = db.getById(moveProObject.getId());

        //these are application errors and the http response should have appropriate code
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
        db.saveGame(game);
        return game;
    }

}


