package palabrasamongamigos.core;

import com.fasterxml.jackson.core.JsonParseException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import palabrasamongamigos.MongoResource;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;


public class GameModelTest {

    GameModel game = new GameModel();

    @Test
    public void testJacksonJSON(){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(game);
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println(json);

        //GameModel readGame = mapper.readValue(json, GameModel.class);
    }

    @Test
    public void testGetFromMongo(){
        final long testID = 1381180168680l;
        MongoResource mongo = MongoResource.INSTANCE;
        DBCollection coll =  mongo.getCollection();
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", testID);
        BasicDBObject fields = new BasicDBObject("_id",false);
        String dbJson = coll.findOne(query, fields).toString();
        //System.out.print(dbJson);
        GameModel test = new GameModel();
        try {
            test = mapper.readValue(dbJson, GameModel.class);
            //System.out.println("GameModel test loaded from db.");
            //System.out.println("id: " + test.getId());
        }
        catch (JsonMappingException e) {
            System.out.println("JSON mapping exception:");
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
        assertEquals(testID, test.getId());
    }

    @Test
    public void testSetErrorMsg(){
        GameModel game = new GameModel();
        assertEquals(null, game.getErrorMsg());
        game.setErrorMsg("ERROR!");
        assertEquals("ERROR!", game.getErrorMsg());
    }
}
