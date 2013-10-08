package palabrasamongamigos.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleScoredWord {

    @JsonProperty
    private String word;
    @JsonProperty
    private int points;
    public SimpleScoredWord(){}
    public SimpleScoredWord(String s, int y) {
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
