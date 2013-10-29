package palabrasamongamigos;

import palabrasamongamigos.core.GameModel;
import palabrasamongamigos.core.PalabrasModel;

public interface DatabaseAccess {

    PalabrasModel getById(long id);

    void save(PalabrasModel model);

    void delete(PalabrasModel model);

    boolean isInDb(PalabrasModel model);

}
