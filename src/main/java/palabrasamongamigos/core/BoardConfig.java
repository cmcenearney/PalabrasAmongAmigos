package palabrasamongamigos.core;

/*
TODO: store this as config data somewhere!
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class BoardConfig {

    public static String plain = " _ ";
    public static String double_letter = " = ";
    public static String double_word = "[=]";
    public static String triple_letter = " ≡ ";
    public static String triple_word = "[≡]";

    public List<ArrayList<String>> scrabble_style = new ArrayList<ArrayList<String>>(15);

    public BoardConfig() {

        String[] row1 = { "triple_word",  "plain",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "triple_word",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "plain",  "triple_word"};
        String[] row2 = { "plain",  "double_word",  "plain",  "plain",  "plain",  "triple_letter",  "plain",  "plain",  "plain",  "triple_letter",  "plain",  "plain",  "plain",  "double_word",  "plain"};
        String[] row3 = { "plain",  "plain",  "double_word",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "double_word",  "plain",  "plain"};
        String[] row4 = { "double_letter",  "plain",  "plain",  "double_word",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "double_word",  "plain",  "plain",  "double_letter"};
        String[] row5 = { "plain",  "plain",  "plain",  "plain",  "double_word",  "plain",  "plain",  "plain",  "plain",  "plain",  "double_word",  "plain",  "plain",  "triple_letter",  "plain"};
        String[] row6 = { "plain",  "triple_letter",  "plain",  "plain",  "plain",  "triple_letter",  "plain",  "plain",  "plain",  "triple_letter",  "plain",  "plain",  "plain",  "plain",  "plain"};
        String[] row7 = { "plain",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "plain"};
        String[] row8 = { "triple_word",  "plain",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "double_word",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "plain",  "triple_word"};
        String[] row9 = { "plain",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "plain"};
        String[] row10 = { "plain",  "triple_letter",  "plain",  "plain",  "plain",  "triple_letter",  "plain",  "plain",  "plain",  "triple_letter",  "plain",  "plain",  "plain",  "triple_letter",  "plain"};
        String[] row11 = { "plain",  "plain",  "plain",  "plain",  "double_word",  "plain",  "plain",  "plain",  "plain",  "plain",  "double_word",  "plain",  "plain",  "plain",  "plain"};
        String[] row12 = { "double_letter",  "plain",  "plain",  "double_word",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "double_word",  "plain",  "plain",  "double_letter"};
        String[] row13 = { "plain",  "plain",  "double_word",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "double_word",  "plain",  "plain"};
        String[] row14 = { "plain",  "double_word",  "plain",  "plain",  "plain",  "triple_letter",  "plain",  "plain",  "plain",  "triple_letter",  "plain",  "plain",  "plain",  "double_word",  "plain" };
        String[] row15 = { "triple_word",  "plain",  "plain",  "double_letter",  "plain",  "plain",  "plain",  "triple_word",  "plain",  "plain",  "plain",  "double_letter",  "plain",  "plain",  "triple_word"};

        scrabble_style.add(new ArrayList<String>(Arrays.asList(row1)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row2)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row3)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row4)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row5)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row6)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row7)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row8)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row9)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row10)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row11)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row12)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row13)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row14)));
        scrabble_style.add(new ArrayList<String>(Arrays.asList(row15)));


    }

}

