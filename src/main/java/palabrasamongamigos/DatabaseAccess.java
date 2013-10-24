package palabrasamongamigos;

import palabrasamongamigos.core.GameModel;

public interface DatabaseAccess {

    GameModel getById(long id);

    void saveGame(GameModel game);

    void deleteGame(GameModel game);

}
