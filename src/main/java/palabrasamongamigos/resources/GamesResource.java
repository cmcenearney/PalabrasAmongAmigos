package palabrasamongamigos.resources;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.util.JSON;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import palabrasamongamigos.MongoResource;
import palabrasamongamigos.core.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Path("/games/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GamesResource {

    protected MongoResource mongo = MongoResource.INSTANCE;
    protected DBCollection coll =  mongo.getCollection();
    public GamesResource() {}

    @POST
    public GameModel makeMove(@PathParam("id") LongParam gameId, @Valid MoveProposal moveProposal) {
        //doing this because instantiation default upper-cases moveString - the json data remains "virtual", no instantiation
        MoveProposal moveProObject = new MoveProposal(moveProposal.getId(),moveProposal.getMoveString(),moveProposal.getMoveNumber(),moveProposal.getCurrentTurn());

        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", moveProposal.getId());
        BasicDBObject fields = new BasicDBObject("_id",false);

        //should this throw an exception if no matching doc found? what kind?
        String dbJson = coll.find(query,fields).next().toString();

        GameModel game = new GameModel();
        try {
            game = mapper.readValue(dbJson, GameModel.class);
        }
        //TODO log exceptions
        catch (JsonMappingException e) {
            System.out.println("mapping exception:");
            System.out.println(e);
            System.out.println(e.getPath());
        }
        catch (JsonParseException e) {
            System.out.println("parse exception:");
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
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
            try {
                String json = mapper.writeValueAsString(game);
                DBObject gameDoc = (DBObject) JSON.parse(json);
                coll.remove(query);
                coll.insert(gameDoc);
            } catch (Exception e){
                System.out.println(e);
            }
        } else {
            game.setErrorMsg(move.getErrorMessage());
        }
        return game;
    }

    @GET
    public GameModel getGame(@PathParam("id") LongParam id){
        final long idLong = id.get();
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", idLong);
        BasicDBObject fields = new BasicDBObject("_id",false);
        String dbJson = coll.findOne(query,fields).toString();
        GameModel game = new GameModel();
        System.out.println(idLong);
        System.out.println(game.getId());
        try {
            game = mapper.readValue(dbJson, GameModel.class);
            System.out.println(game.getId());
        }
        //TODO log exceptions
        catch (JsonMappingException e) {
            System.out.println("mapping exception:");
            System.out.println(e);
            System.out.println(e.getPath());
        }
        catch (JsonParseException e) {
            System.out.println("parse exception:");
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        return game;
    }
}

/*
curl http://localhost:8080/api/games/1381106790975  -X POST -H "Content-Type: application/json" -d '{ "id": 1381106790975, "moveString": "H,8,>,HOG", "moveNumber": 1, "currentTurn": 0}'

        if ( move.checkMove() )  {
            int score = move.makeMove();
            model.moves.add(move);
            view.printLine("Well done, that move scored " + score + " points.");
            int new_total_score = current_player.getScore() + score;
            current_player.setScore( new_total_score );
            while (current_player.getTiles().size() < model.num_tiles && model.tile_bag.getTiles().size() > 0){
                current_player.addTile(model.tile_bag.randomDraw());
            }
            return true;
        }
        else {
            //print message? Move will print its own details about why not valid?
            view.printLine(move.getError_message());
            return false;
        }
consumes json a la
      {
        "id": 123456789,
        "moveString": "a,12,>,word",
        "moveNumber": 2,
        "currentTurn": 0
      }


validate:
  - load from DB game[@id = json.id]
  - check that:
    - json.moveNumber == game.moves.length - 1
    - game.currentTurn == json.currentTurn
  - if move is valid
    - make move
    return game
*/
