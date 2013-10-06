package palabrasamongamigos.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.codehaus.jackson.annotate.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameModel implements Serializable{

    //attributes
    public static final int num_tiles = 7;
    private Dictionary dictionary = Dictionary.getDictionaryInstance();
    @Expose
    @JsonProperty
    protected Board board = new Board();
    @Expose
    @JsonProperty
    protected List<Player> players = new ArrayList<Player>();
    @Expose
    @JsonProperty
    protected List<Move> moves = new ArrayList<Move>();
    @Expose
    @JsonProperty
    protected Integer num_players = 2;
    @Expose
    @JsonProperty
    protected Integer current_turn = 0;
    @Expose
    @JsonProperty
    protected TileBag tile_bag = new TileBag();
    protected boolean is_first_move = true;
    @Expose
    @JsonProperty
    protected long id;

    //constructors
    public GameModel(){
        //this.createDictionary();
        this.id = produceUniqueID();
        addPlayers();
    }

    public GameModel(long id){
        //this.createDictionary();
        this.id = id;
        addPlayers();
    }

    //getters + setters
    public List<Player> getPlayers() {
        return players;
    }
    public Integer getNum_players() {
        return num_players;
    }
    public void setNum_players(int n) {
        this.num_players=n;
    }
    public Integer getCurrent_turn() {
        return current_turn;
    }
    public TileBag getTile_bag() {
        return tile_bag;
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
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    //methods

    public void addPlayers(){
        for (int i = 0; i < num_players; i++){
            //TODO: handle player name
            Player player = new Player("", this);
            players.add(i,player);
            for (int j=0; j < num_tiles; j++){
                Tile t = tile_bag.randomDraw();
                player.addTile(t);
            }
        }
    }
    /*
    public void createDictionary() {
        File file = new File("resources/words.txt");
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.length() > 0) {
                    this.dictionary.add(line.toUpperCase());
                }
            }
        }
        catch (IOException e){
            System.out.println("problem reading dictionary file:" + e);
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
    public void showJSON(){
        JSONObject json = new JSONObject();
        try {
            json.put("board", this.board.spaces);
        }
        catch (JSONException e) {}
        System.out.println(json.toString());
    }

    public String returnGSON(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(this);
        return json;
    }

    public void showGSON(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(this);
        System.out.println(json);
    }
}

