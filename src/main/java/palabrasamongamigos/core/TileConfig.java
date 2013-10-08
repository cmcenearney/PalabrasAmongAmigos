package palabrasamongamigos.core;

import java.util.HashMap;

/*
copy tile distribution from resources/letterFrequencies.txt
TODO: store this as config data in a file
 */


public class TileConfig {

    public class Tuple {
        public final int number;
        public final int points;
        public Tuple(int x, int y) {
            this.number = x;
            this.points = y;
        }
    }

    public static final HashMap<String, Tuple> tile_config = new HashMap<String, Tuple>();

    public TileConfig() {
        // using Tuple format to store the number of tiles and point value
        tile_config.put("E", new Tuple(12, 1));
        tile_config.put("A",  new Tuple(9, 1));
        tile_config.put("I",  new Tuple(9, 1));
        tile_config.put("O",  new Tuple(8, 1));
        tile_config.put("N",  new Tuple(6, 1));
        tile_config.put("R",  new Tuple(6, 1));
        tile_config.put("T",  new Tuple(6, 1));
        tile_config.put("L",  new Tuple(4, 1));
        tile_config.put("S",  new Tuple(4, 1));
        tile_config.put("U",  new Tuple(4, 1));
        tile_config.put("D",  new Tuple(4, 2));
        tile_config.put("G",  new Tuple(3, 2));
        tile_config.put("B",  new Tuple(2, 3));
        tile_config.put("C",  new Tuple(2, 3));
        tile_config.put("M",  new Tuple(2, 3));
        tile_config.put("P",  new Tuple(2, 3));
        tile_config.put("F",  new Tuple(2, 4));
        tile_config.put("H",  new Tuple(2, 4));
        tile_config.put("V",  new Tuple(2, 4));
        tile_config.put("W",  new Tuple(2, 4));
        tile_config.put("Y",  new Tuple(2, 4));
        tile_config.put("K",  new Tuple(1, 5));
        tile_config.put("J",  new Tuple(1, 8));
        tile_config.put("X",  new Tuple(1, 8));
        tile_config.put("Q",  new Tuple(1, 10));
        tile_config.put("Z",  new Tuple(1, 10));
    }

}
