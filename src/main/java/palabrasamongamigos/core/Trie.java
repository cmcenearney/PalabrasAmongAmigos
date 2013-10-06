package palabrasamongamigos.core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {

    private TrieNode root = new TrieNode("");

    public Trie() {}

    public Trie(List<String> words){
        for (String word : words){
            addWord(word);
        }
    }

    public void addWord(String word){
        TrieNode currentNode = root;
        char chars[] = word.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if (!currentNode.containsChildValue(chars[i])){
                currentNode.addChild(chars[i], new TrieNode(currentNode.getValue() + chars[i]));
            }
            currentNode = currentNode.getChild(chars[i]);
        }
        currentNode.setIsWord(true);
    }

    public TrieNode getNode(String word){
        TrieNode currentNode = root;
        char chars[] = word.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if (currentNode.containsChildValue(chars[i])){
                currentNode = currentNode.getChild(chars[i]);
            }
            else {
                return null;
            }
        }
        return currentNode;
    }

    public boolean containsPrefix(String word) {
        return (getNode(word) != null && !getNode(word).isWord());
    }

    public boolean containsWord(String word) {
        return (getNode(word) != null && getNode(word).isWord());
    }

    public ArrayList<String> listWords(){
        ArrayList<String> words = new ArrayList<String>();
        return listWords(root, words);
    }
    public ArrayList<String> listWords(TrieNode node, ArrayList<String> words){
        TrieNode currentNode = node;
        if (currentNode.isWord()) {
            words.add(currentNode.getValue());
        }
        if (currentNode.hasChildren()){
            for (TrieNode n : currentNode.getChildren() ) {
                listWords(n, words);
            }
        }
        return words;
    }

}

