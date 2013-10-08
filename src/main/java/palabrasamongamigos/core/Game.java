package palabrasamongamigos.core;

import com.mongodb.DBObject;

public class Game {

    private final long id;
    private String content;

    public Game(long id, String content){
        this.id = id;
        this.content = content;
    }

    //Jackson uses getters
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    //good enough for now
    long produceUniqueID(){
        return System.currentTimeMillis();
    }

}
