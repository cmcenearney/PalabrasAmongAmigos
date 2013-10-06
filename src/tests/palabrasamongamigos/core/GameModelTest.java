package palabrasamongamigos.core;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import palabrasamongamigos.MongoResource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GameModelTest {

    GameModel game = new GameModel();

    @Test
    public void testJacksonJSON(){
        //game.showGSON();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(game);
        } catch (Exception e){
            System.out.println(e);
        }
        //System.out.println(json);
    }
     /*
    @Test
    public void testGetFromMongo(){
        MongoResource mongo = MongoResource.INSTANCE;
        DBCollection coll =  mongo.getCollection();
        BasicDBObject query = new BasicDBObject("id", 1);
        assertEquals(coll.find(query).count(), 5);

    }
    */
}
