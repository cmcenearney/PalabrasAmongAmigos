package palabrasamongamigos.core;

import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Board implements Serializable{

    public static final int board_size = 15;
    @JsonProperty
    protected List<ArrayList<BoardSpace>> spaces = new ArrayList<ArrayList<BoardSpace>>(board_size);

    public HashMap<String,String> empty_space_values = new HashMap<String, String>();

    public Board(){

        BoardConfig config = new BoardConfig();

        empty_space_values.put("plain", config.plain);
        empty_space_values.put("double_letter", config.double_letter);
        empty_space_values.put("double_word", config.double_word);
        empty_space_values.put("triple_letter", config.triple_letter);
        empty_space_values.put("triple_word", config.triple_word);

        for (int row = 0; row < board_size; row++){
            spaces.add(row, new ArrayList<BoardSpace>(15));
            for (int col = 0; col < board_size; col++){
                String type = config.scrabble_style.get(row).get(col);
                BoardSpace new_space = new BoardSpace(type);
                //new_space.setValue(empty_space_values.get(type));
                spaces.get(row).add(col, new_space);
            }
        }

    }

    public BoardSpace getSpace(int row, int column){
        return this.spaces.get(row).get(column);
    }

    public ArrayList<BoardSpace> getRow(int row){
        return spaces.get(row);
    }

    public ArrayList<BoardSpace> getColumn(int col){
        ArrayList<BoardSpace> column = new ArrayList<BoardSpace>(15);
        for (int row=0; row < board_size; row++) {
            BoardSpace space = this.getSpace(row,col);
            column.add(row, space);
        }
        return column;
    }

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

}
