class Solution {

    private int[][] loc = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    private boolean[][] visited;

    private Set<String> ans;

    public List<String> findWords(char[][] board, String[] words) {
        ans = new HashSet<>();
        if(null == board || 0 == board.length || 0 == board[0].length || null == words || 0 == words.length) {
            return new ArrayList<>();
        }

        visited = new boolean[board.length][board[0].length];
        Trie head = new Trie();
        for(String s: words) {
            head.insert(s);
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, head, new StringBuilder());
            }
        }
        return new ArrayList<>(ans);
    }

    private void dfs(char[][] board, int i, int j, Trie cur, StringBuilder sb) {
        if(visited[i][j]) {
            return;
        }

        Trie next = cur.getNext(board[i][j]);
        if(null == next) {
            return;
        }

        visited[i][j] = true;
        sb.append(board[i][j]);
        if(next.isEnd) {
            ans.add(sb.toString());
        }

        for(int[] dire: loc) {
            int x = i + dire[0], y = j + dire[1];
            if(x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y]) {
                dfs(board, x, y, next, sb);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        visited[i][j] = false;
    }

    class Trie {

        Trie[] next;

        boolean isEnd;

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
                Trie ne = temp.next[c - 'a'];
                if(null == ne) {
                    ne = new Trie();
                    temp.next[c - 'a'] = ne;
                }
                temp = ne;
            }
            temp.isEnd = true;
        }

        public Trie getNext(char c) {
            return next[c - 'a'];
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
}