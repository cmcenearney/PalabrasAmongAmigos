package palabrasamongamigos.core;

import com.fasterxml.jackson.core.JsonParseException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import palabrasamongamigos.MongoResource;
import java.io.IOException;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class GameInGameOut {

    GameModel game = new GameModel();

    @Test
    public void sameInAsOutNewGame(){
        System.out.println(game.getId());
        MongoResource mongo = MongoResource.INSTANCE;
        DBCollection coll =  mongo.getCollection();
        ObjectMapper mapper = new ObjectMapper();
        String jsonIn = "";
        try {
            jsonIn = mapper.writeValueAsString(game);
        } catch (Exception e){
            System.out.println(e);
        }
        DBObject gameDoc = (DBObject) JSON.parse(jsonIn);
        coll.insert(gameDoc);
        BasicDBObject query = new BasicDBObject("id", game.getId());
        BasicDBObject fields = new BasicDBObject("_id",false);
        String dbJson = coll.findOne(query,fields).toString();


        assertJsonEquals(jsonIn, dbJson);
        //System.out.println(dbJson);
        System.out.println(jsonIn);
        GameModel fromDB = new GameModel();
        System.out.println(fromDB.getId());
        try {
            fromDB = mapper.readValue(dbJson, GameModel.class);
        }
        //TODO log exceptions
        catch (com.fasterxml.jackson.databind.JsonMappingException e) {
            System.out.println("mapping exception:");
            System.out.println(e);
            System.out.println(e.getPath());
        }
        catch (JsonParseException e) {
            System.out.println("parse exception:");
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println("IO exception:");
            System.out.println(e);
        }
        assertEquals(fromDB.getId(), game.getId());
        System.out.println(fromDB.getId());
    }
}
