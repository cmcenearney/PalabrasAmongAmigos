package palabrasamongamigos.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    private final String value;
    private Character ch;
    private boolean isWord = false;
    private Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();

    public TrieNode(String value){
        this.value = value;
    }

    public boolean addChild(char c, TrieNode argChild) {
        children.put(c, argChild);
        return true;
    }

    public boolean containsChildValue(char c) {
        return children.containsKey(c);
    }

    public String getValue(){
        return this.value;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setIsWord(boolean isWord){
        this.isWord = isWord;
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public ArrayList<TrieNode> getChildren(){
        ArrayList<TrieNode> results = new ArrayList<TrieNode>();
        for (Map.Entry<Character, TrieNode> entry : this.children.entrySet()){
            results.add(entry.getValue());
        }
        return results;
    }

}


