package palabrasamongamigos.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionModel extends PalabrasModel {

    @JsonProperty
    private long id;
    @JsonProperty
    private long gameID;
    @JsonProperty
    private Player player;
    @JsonProperty
    private String playerEmail;

    public SessionModel() {}

    public SessionModel(long gameID, Player player) {
        this.gameID = gameID;
        this.player = player;
        this.playerEmail = player.getEmail();
        this.id = produceUniqueID();
    }

    public SessionModel(long gameID, String playerEmail) {
        this.gameID = gameID;
        this.playerEmail = playerEmail;
        this.id = produceUniqueID();
    }

    public String getPlayerEmail() {
        return playerEmail;
    }

    public long getGameID() {
        return gameID;
    }

    public long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }



}
