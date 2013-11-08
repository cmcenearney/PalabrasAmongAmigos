package palabrasamongamigos.health;

import com.yammer.metrics.core.HealthCheck;
import palabrasamongamigos.DatabaseAccessor;


public class MongoHealthCheck extends HealthCheck {

    private final DatabaseAccessor mongo;

    public MongoHealthCheck(DatabaseAccessor mongo){
        super("mongo");
        this.mongo = mongo;
    }

    @Override
    protected Result check() throws Exception {
        if (mongo.getCollection().equals("palabras")) {
            return Result.healthy();
        } else {
            return Result.unhealthy("Can't access MongoDB collection 'palabras");
        }
    }
}
