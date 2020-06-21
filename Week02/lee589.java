class Solution {

    /**
     * 方法1：一开始自己写的使用数据栈遍历，操作有点奇怪，stack中存的是node列表，遍历后删除遍历过的节点，代码不够简洁
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if(null == root) {
            return res;
        }

        Stack<List<Node>> stack = new Stack<>();
        stack.push(new ArrayList<>(Arrays.asList(root)));
        while(!stack.empty()) {
            List<Node> nodes = stack.pop();
            if(null != nodes && !nodes.isEmpty()) {
                Node first = nodes.get(0);
                res.add(first.val);
                nodes.remove(0);

                if(!nodes.isEmpty()) {
                    stack.push(nodes);
                }

                if(null != first.children && !first.children.isEmpty()) {
                    stack.push(first.children);
                }
            }
        }
        return res;
    }

    /**
     * 方法2：题解数据栈遍历，将所有节点放到stack，从后往前遍历，代码简洁，但是不易理解
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.add(node.val);
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }
        return output;
    }
}