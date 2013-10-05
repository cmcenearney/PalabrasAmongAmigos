import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.util.List;
import java.util.Set;

public class MongoEXP {

    public static void main(String[] args) throws Exception {

        // connect to the local database server
        //MongoClient mongoClient = new MongoClient();
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        // Authenticate - optional
        // boolean auth = db.authenticate("foo", "bar");

        // get db names
        for (String s : mongoClient.getDatabaseNames()) {
            System.out.println(s);
        }

        DB db = mongoClient.getDB( "palabras" );

        // get db names
        for (String s : mongoClient.getDatabaseNames()) {
            System.out.println(s);
        }

    }
}
