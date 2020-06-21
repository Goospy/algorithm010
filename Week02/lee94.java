public class L94 {

    /**
     * 自己先实现的使用数据栈实现中序遍历，需要使用visited这样一个map记录是否访问过，有点不是很优雅
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(null == root) {
            return list;
        }

        Map<TreeNode, Boolean> visited = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if(null != node.left && !visited.getOrDefault(node.left, false)) {
                visited.put(node.left, true);
                stack.push(node.left);
                continue;
            }
            stack.pop();
            list.add(node.val);

            if(null != node.right && !visited.getOrDefault(node.right, false)) {
                visited.put(node.right, true);
                stack.push(node.right);
            }
        }

        return list;
    }

    /**
     * 题解的标准答案，自己实现了下，比较优雅
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(null == root) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(null != node || !stack.isEmpty()) {
            while(null != node) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }

        return list;
    }

    /**
     * 递归实现
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal3(root, list);
        return list;
    }

    private void inorderTraversal3(TreeNode root, List<Integer> list) {
        if(null == root) {
            return;
        }
        inorderTraversal3(root.left, list);
        list.add(root.val);
        inorderTraversal3(root.right, list);
    }

}
