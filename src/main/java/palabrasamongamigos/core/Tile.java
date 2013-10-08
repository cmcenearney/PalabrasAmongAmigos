package palabrasamongamigos.core;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Tile implements Serializable {
    @JsonProperty
    private String character;
    @JsonProperty
    private int points;

    public Tile(){}

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

