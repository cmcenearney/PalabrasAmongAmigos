package palabrasamongamigos.core;

import com.fasterxml.jackson.core.JsonParseException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import palabrasamongamigos.MongoResource;
import java.io.IOException;

public class MoveTest {

    GameModel game = new GameModel();

    @Before
    public void loadFailedGame(){
        final long id = 1381229484877l;
        MongoResource mongo = MongoResource.INSTANCE;
        DBCollection coll =  mongo.getCollection();
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", id);
        BasicDBObject fields = new BasicDBObject("_id",false);
        String dbJson = coll.findOne(query,fields).toString();
        try {
            game = mapper.readValue(dbJson, GameModel.class);
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
        System.out.println(game.getId());
    }

    @Test
    public void testFailedGame(){
        System.out.println(game.getId());
    }
}
