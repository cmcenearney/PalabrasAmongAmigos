package palabrasamongamigos.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Board implements Serializable{

    public static final int boardSize = 15;
    @JsonProperty
    private List<ArrayList<BoardSpace>> spaces = new ArrayList<ArrayList<BoardSpace>>(boardSize);

    private HashMap<String,String> emptySpaceValues = new HashMap<String, String>();



    public Board(){

        BoardConfig config = new BoardConfig();

        emptySpaceValues.put("plain", config.plain);
        emptySpaceValues.put("double_letter", config.double_letter);
        emptySpaceValues.put("double_word", config.double_word);
        emptySpaceValues.put("triple_letter", config.triple_letter);
        emptySpaceValues.put("triple_word", config.triple_word);

        for (int row = 0; row < boardSize; row++){
            spaces.add(row, new ArrayList<BoardSpace>(15));
            for (int col = 0; col < boardSize; col++){
                String type = config.scrabble_style.get(row).get(col);
                BoardSpace new_space = new BoardSpace(type);
                //new_space.setValue(emptySpaceValues.get(type));
                spaces.get(row).add(col, new_space);
            }
        }

    }
    
    public List<ArrayList<BoardSpace>> getSpaces(){
        return this.spaces;
    }
    
    public BoardSpace getSpace(int row, int column){
        return this.spaces.get(row).get(column);
    }

    public ArrayList<BoardSpace> getRow(int row){
        return spaces.get(row);
    }

    public ArrayList<BoardSpace> getColumn(int col){
        ArrayList<BoardSpace> column = new ArrayList<BoardSpace>(15);
        for (int row=0; row < boardSize; row++) {
            BoardSpace space = this.getSpace(row,col);
            column.add(row, space);
        }
        return column;
    }
    /*
    public boolean isEmpty(){
        for (ArrayList<BoardSpace> row : spaces){
            for (BoardSpace s : row){
                if (s.isOccupied()){
                    return false;
                }
            }
        }
        return true;
    }
    */
}
