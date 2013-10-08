package palabrasamongamigos.core;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BoardSpace implements Serializable {

    private static final String[] valid_types = {"double_letter","double_word","triple_letter", "triple_word", "plain"};
    @JsonProperty
    private String type = "plain";
    @JsonProperty
    private String value = null;
    @JsonProperty
    private boolean occupied = false;

    public BoardSpace(){
    }

    // question: does it make sense to enforce validity of the type here?
    // and if so, how - exception? invalid defaults to normal?
    public BoardSpace(String type){
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        if (value != null){
            this.occupied = true;
        }
    }

    public String getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

}


