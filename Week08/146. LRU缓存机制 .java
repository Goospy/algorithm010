class LRUCache {

    private Map<Integer, Node> nodeMap;

    private Node head;

    private Node tail;

    private int cap;

    private int count;

    public LRUCache(int capacity) {
        nodeMap = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        cap = capacity;
        count = 0;
    }

    public int get(int key) {
        if(nodeMap.containsKey(key)) {
            Node n = nodeMap.get(key);
            deleteNode(n);
            moveToHead(n);
            return nodeMap.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node n;
        if(!nodeMap.containsKey(key)) {
            n = new Node(key, value);

            if(count == cap) {
                Node old = deleteTail();
                nodeMap.remove(old.key);
                count--;
            }
        } else {
            n = nodeMap.get(key);
            n.val = value;

            deleteNode(n);
            count--;
        }

        count++;
        moveToHead(n);
        nodeMap.put(key, n);
    }

    private void deleteNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void moveToHead(Node node) {
        head.next.pre = node;
        node.next = head.next;
        head.next = node;
        node.pre = head;
    }

    private Node deleteTail() {
        Node node = tail.pre;
        deleteNode(node);
        return node;
    }
}

class Node {
    Node pre;
    Node next;
    int val;
    int key;

    Node(int key, int val) {
        this.val = val;
        this.key = key;
    }
}
