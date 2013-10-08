package palabrasamongamigos.core;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DictionaryTest {

    private Dictionary dictionary = Dictionary.getDictionaryInstance();

    @Test
    public void dictionaryIsLoading(){
        assertEquals(true, dictionary.validWord("ti"));
    }
}
