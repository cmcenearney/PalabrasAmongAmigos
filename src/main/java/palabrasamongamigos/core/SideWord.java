package palabrasamongamigos.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SideWord {

    @JsonProperty
    private String word;
    @JsonProperty
    private int points;
    public SideWord(){}
    public SideWord(String s, int y) {
        this.word = s;
        this.points = y;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
}
