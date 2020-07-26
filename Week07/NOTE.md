##学习笔记

###字典树
1.用途：在O(K)时间复杂度内查找字符串是否在给定字符串集合内,K是字符串集合中的最长字符串长度，是用空间换时间。

2.代码实现
```java
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
```

2.单词搜索 2 用 Tire 树方式实现的时间复杂度
O(n^2*K),每个节点都会查找一遍，但是不用遍历查找字符串。

###并查集
1.代码实现
```java
public class UnionFound {

    private int[] parent;

    private int[] rank;

    public UnionFound(int n) {
        parent = new int[n];
        rank = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int p){
        while(parent[p] != p) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean connect(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot) {
            return;
        }

        if(rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else if(rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
```

###双向BFS搜索
代码模版
```java
    public void bfs(Node start, Node end) {
        Deque<Node> start = new ArrayDeque<>();
        deque.push(start);

        Deque<Node> end = new ArrayDeque<>();
        deque.push(end);

        boolean meet = false;
        Set<Node> stNodes = new HashSet<>();
        Set<Node> edNodes = new HashSet<>();

        while(!start.isEmpty() && end.isEmpty() && !meet) {
            Deque<Node> temp = new ArrayDeque<>();
            while(!start.isEmpty()) {
                Node n = start.poll();
                stNodes.add(n);
                temp.add(getNext(n));
            }  
            start = temp;         
        
            temp = new ArrayDeque<>();
            while(!end.isEmpty()) {
                Node n = end.poll();
                edNodes.add(n);
                temp.add(getNext(n));
            }  
            end = temp;  
                   
            if(hasUnion(stNodes, edNodes)) {
                meet = true;
            }   
        }

        return meet;
    }
```