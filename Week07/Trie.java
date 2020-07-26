class Trie {

    private Trie[] next;

    private boolean isEnd;

    /** Initialize your data structure here. */
    public Trie() {
        next = new Trie[26];
        isEnd = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(null == word || 0 == word.length()) {
            return;
        }

        Trie temp = this;
        for(char c: word.toCharArray()) {
            if(null == temp.next[c - 'a']) {
                temp.next[c - 'a'] = new Trie();
            }
            temp = temp.next[c - 'a'];
        }
        temp.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie end = getEndNode(word);
        return end != null && end.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return getEndNode(prefix) != null;
    }

    private Trie getEndNode(String prefix) {
        if(null == prefix || 0 == prefix.length()) {
            return null;
        }

        Trie temp = this;
        for(char c: prefix.toCharArray()) {
            Trie ne = temp.next[c - 'a'];
            if(null == ne) {
                return null;
            }
            temp = ne;
        }
        return temp;
    }
}
