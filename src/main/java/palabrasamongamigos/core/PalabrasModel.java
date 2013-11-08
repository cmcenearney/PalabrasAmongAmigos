package palabrasamongamigos.core;

/**
 * simple class for persisted models to extend
 */
public class PalabrasModel {

    private long id;

    public PalabrasModel() {
        this.id = produceUniqueID();
    }

    //good enough for now
    long produceUniqueID(){
        return System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
