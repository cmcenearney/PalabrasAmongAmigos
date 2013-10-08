package palabrasamongamigos.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/*
singleton implementations
 - pros and cons vs going all static?
*/
public class Dictionary {

    private static Dictionary singletonInstance;

    private HashSet<String> dictionary = new HashSet<String>();

    private Dictionary() {
        this.createDictionary();
    }

    public static Dictionary getDictionaryInstance() {
        if (null == singletonInstance) {
            singletonInstance = new Dictionary();
        }
        return singletonInstance;
    }

    //methods
    private void createDictionary() {
        File file = new File("src/main/resources/words.txt");
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

    public boolean validWord(String word){
        return dictionary.contains(word.toUpperCase());
    }
}



