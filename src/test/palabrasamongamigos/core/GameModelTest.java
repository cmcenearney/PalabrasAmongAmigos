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
import static org.junit.Assert.assertThat;

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
    }

    @Test
    public void testGetFromMongo(){
        MongoResource mongo = MongoResource.INSTANCE;
        DBCollection coll =  mongo.getCollection();
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", 1381098802449l);
        BasicDBObject fields = new BasicDBObject("_id",false);
        String dbJson = coll.find(query,fields).next().toString();
        //System.out.print(dbJson);
        GameModel test = new GameModel();
        try {
            test = mapper.readValue(dbJson, GameModel.class);
            System.out.println("GameModel test loaded from db.");
            System.out.println("id: " + test.getId());
        }
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
        assertEquals(1381098802449l, test.getId());
    }

}
