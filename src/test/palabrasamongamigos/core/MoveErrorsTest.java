package palabrasamongamigos.core;

import org.junit.Test;

import static com.mongodb.util.MyAsserts.assertTrue;

public class MoveErrorsTest {

    private GameModel game = new GameModel();

    @Test
    public void wordTest() {
       assertTrue(game.validWord("cam"));
    }


}
