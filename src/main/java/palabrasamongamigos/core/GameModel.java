package palabrasamongamigos.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameModel implements Serializable{

    //attributes
    public static final int num_tiles = 7;
    private Dictionary dictionary = Dictionary.getDictionaryInstance();
    private String _id;

    @JsonProperty
    protected Board board = new Board();
    @JsonProperty
    protected List<Player> players = new ArrayList<Player>();
    @JsonProperty
    protected List<Move> moves = new ArrayList<Move>();
    @JsonProperty
    protected Integer numPlayers = 2;
    @JsonProperty
    protected Integer currentTurn = 0;
    @JsonProperty
    protected TileBag tileBag = new TileBag();
    @JsonProperty
    protected long id;
    @JsonProperty
    protected String errorMsg=null;

    //constructors
    public GameModel(){
        this.id = produceUniqueID();
        //addPlayers();
    }
    public GameModel(Player player){
        this.id = produceUniqueID();
        this.players.add(player);
    }

    public GameModel(long id){
        this.id = id;
        //addPlayers();
    }

    //getters + setters
    public List<Player> getPlayers() {
        return players;
    }
    public Integer getNumPlayers() {
        return numPlayers;
    }
    public void setNumPlayers(int n) {
        this.numPlayers=n;
    }
    public Integer getCurrentTurn() {
        return currentTurn;
    }
    public TileBag getTileBag() {
        return tileBag;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public List<Move> getMoves() {
        return moves;
    }
    public Board getBoard() {
        return board;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    //methods
    /*
    public void addPlayers(){
        for (int i = 0; i < numPlayers; i++){
            //TODO: handle player name
            Player player = new Player("", this);
            players.add(i,player);
            for (int j=0; j < num_tiles; j++){
                Tile t = tileBag.randomDraw();
                player.addTile(t);
            }
        }
    }
     */
    public boolean validWord(String word){
        return this.dictionary.validWord(word.toUpperCase());
    }

    //good enough for now
    long produceUniqueID(){
        return System.currentTimeMillis();
    }

    public void serializeToFile(){
        try{
            FileOutputStream fout = new FileOutputStream("resources/games/" + this.id + ".ser" );
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            oos.close();
            System.out.println("Game saved");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}

