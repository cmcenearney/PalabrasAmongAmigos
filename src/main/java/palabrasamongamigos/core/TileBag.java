package palabrasamongamigos.core;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TileBag implements Serializable{
    @JsonProperty
    private List<Tile> tiles = new ArrayList<Tile>();

    public TileBag(){
        TileConfig config = new TileConfig();
        for (String key : config.tile_config.keySet()) {
            TileConfig.Tuple tuple = config.tile_config.get(key);
            int num = tuple.number;
            int points = tuple.points;
            for (int i = 1; i <= num; i++){
                tiles.add(new Tile(key, points));
            }
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void addTile(Tile t) {
        tiles.add(t);
    }

    public Tile randomDraw(){
        int random_index = (int)(Math.random() * tiles.size());
        return tiles.remove(random_index);
    }

    public Tile getTileByChar(String character){
        for (Tile t : tiles){
            if ( t.getCharacter().equals(character) ) {
                return t;
            }
        }
        return new Tile(null, 0);
    }

}
