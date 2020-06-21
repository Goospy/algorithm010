public class L144 {

    /**
     * 数据栈实现先序遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(null == root) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if(null != node.right) {
                stack.push(node.right);
            }
            if(null != node.left) {
                stack.push(node.left);
            }
        }
        return list;
    }

    /**
     * 递归实现
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal2(root, list);
        return list;
    }

    private void preorderTraversal2(TreeNode root, List<Integer> list) {
        if(null == root) {
            return;
        }
        list.add(root.val);
        preorderTraversal2(root.left, list);
        preorderTraversal2(root.right, list);
    }

}