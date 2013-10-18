package palabrasamongamigos;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;

public enum MongoResource {
    INSTANCE;
    private MongoClient mongoClient;
    private DB db;
    DBCollection collection;

    private MongoResource() {
        //MongoClientURI mongoUri  = new MongoClientURI ( (System.getenv("MONGOLAB_URI") != null) ? System.getenv("MONGOLAB_URI") : "mongodb://localhost:27017");
        //MongoClient mongoClient;
        try {
            if (System.getenv("MONGOLAB_URI") != null)  {

                MongoClientURI mongoUri  = new MongoClientURI (System.getenv("MONGOLAB_URI"));
                mongoClient = new MongoClient(mongoUri);
                String dbName = mongoUri.getDatabase();
                db = mongoClient.getDB( dbName );

            }  else {
                mongoClient = new MongoClient( "localhost" , 27017 );
                db = mongoClient.getDB( "palabras" );
            }
            //db = mongoClient.getDB( "palabras" );
            collection = db.getCollection("games");
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
    }

    public DBCollection getCollection(){
        return collection;
    }

}