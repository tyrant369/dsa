/**
 *  #Leetcode 208 | https://leetcode.com/problems/implement-trie-prefix-tree/description/
 */


public  class ImplementTrie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;

        for (char w : word.toCharArray()) {
            if (!node.containsKey(w)) {
                node.put(w, new TrieNode());
            }

            node = node.get(w);
        }

        node.setEnd();
    }

    public boolean search(String word) {
        TrieNode node = root;

        for (char w : word.toCharArray()) {
            if (!node.containsKey(w)) {
                return false;
            }

            node = node.get(w);
        }

        return node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char pre : prefix.toCharArray()) {
            if (!node.containsKey(pre)) {
                return false;
            }

            node = node.get(pre);
        }

        return true;
    }
}

public class TrieNode {
    TrieNode[] links = new TrieNode[26];
    boolean flag = false;

    public boolean containsKey (char ch) {
        return links[ch - 'a'] != null;
    }

    public void put (char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public TrieNode get (char ch) {
        return links[ch - 'a'];
    }

    public void setEnd () {
        flag = true;
    }

    public boolean isEnd() {
        return flag;
    }
}