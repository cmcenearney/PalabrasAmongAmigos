package palabrasamongamigos;

import palabrasamongamigos.core.GameModel;
import palabrasamongamigos.core.PalabrasModel;
import palabrasamongamigos.core.SessionModel;

public interface DatabaseAccess {

    PalabrasModel getGameById(long id);

    SessionModel getSessionById(long id);

    void save(PalabrasModel model);

    void delete(PalabrasModel model);

    boolean isInDb(PalabrasModel model);

}
