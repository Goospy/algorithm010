public class L429 {

    /**
     * 使用队列存储下一层节点，然后再遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> lists = new LinkedList<>();
        if(null == root) {
            return lists;
        }

        List<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while(!nodes.isEmpty()) {
            List<Node> childNodes = new LinkedList<>();
            List<Integer> list = new LinkedList<>();
            for(Node node: nodes) {
                list.add(node.val);
                childNodes.addAll(node.children);
            }
            lists.add(list);
            nodes = childNodes;
        }
        return lists;
    }

}