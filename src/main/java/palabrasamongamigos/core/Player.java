package palabrasamongamigos.core;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player implements Serializable, Comparable<Player > {

    //attributes
    @JsonProperty
    private String name;
    @JsonProperty
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    @JsonProperty
    private int score = 0;
    private GameModel game;


    //constructors
    public Player() {}

    public Player(GameModel game) {
        this.game = game;
    }

    public Player(String name, GameModel game) {
        this.name = name;
        this.game = game;
    }

    public Player(String name, ArrayList<Tile> tiles, int score) {
        this.name = name;
        this.tiles = tiles;
        this.score = score;
    }

    //getters + setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Tile> getTiles(){
        ArrayList<Tile> tiles_copy = new ArrayList<Tile>();
        for (Tile tile : this.tiles) {
            tiles_copy.add(tile);
        }
        return tiles_copy;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    //implement Comparable
    public int compareTo(Player p)
    {
        return(score - p.score);
    }


    //methods
    public void addTile(Tile tile){
        tiles.add(tile);
    }

    public String listTiles(){
        String output = "[ ";
        for (Tile t : tiles){
            output += t.getCharacter();
            output += " ";
        }
        output += "]";
        return output;
    }

    public ArrayList<String> tileValues(){
        ArrayList<String> tile_values = new ArrayList<String>();
        for (Tile t : tiles){
            tile_values.add(t.getCharacter());
        }
        return tile_values;
    }

    public boolean removeTile(String s){
        for (Tile t : tiles){
            if (t.getCharacter().equals(s)){
                tiles.remove(t);
                return true;
            }
        }
        return false;
    }

    //doesn't handle user error: if exchanges contains a char not in tiles
    public ArrayList<String> exchangeTiles(ArrayList<String> exchanges){
        ArrayList<Tile> ex_tiles = new ArrayList<Tile>();
        while (exchanges.size() > 0){
            String s = exchanges.get(0);
            for (Tile t : tiles){
                if (t.getCharacter().equals(s)){
                    game.getTileBag().addTile(t);
                    ex_tiles.add(t);
                    exchanges.remove(s);
                }
            }
        }
        for (Tile t : ex_tiles){
            tiles.remove(t);
        }
        while (tiles.size() < GameModel.num_tiles){
            tiles.add(game.getTileBag().randomDraw());
        }
        return exchanges;
    }

    /*
    Fisher-Yates shuffle algorithm
    http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
    */
    public void shuffleTiles(){
        for (int i=GameModel.num_tiles-1; i > 0; i--){
            int random_index = (int) Math.floor((Math.random()*i)+0);
            Tile tmp = tiles.get(random_index);
            tiles.set(random_index,tiles.get(i));
            tiles.set(i, tmp);
        }
    };

}
