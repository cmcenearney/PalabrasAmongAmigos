package palabrasamongamigos.core;

import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;

public class Tile implements Serializable {
    @JsonProperty
    private String character;
    @JsonProperty
    private int points;

    public Tile(String ch, int pts){
        this.character = ch;
        this.points = pts;
    }

    public String getCharacter() {
        return character;
    }

    public int getPoints() {
        return points;
    }

}

