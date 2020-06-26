#学习笔记

##递归

###1.定义
递归就是通过调用自身函数求解，父问题的解依赖子问题的解，而父问题与子问题的求解逻辑是重叠的或近似重叠。

###2.递归代码模版

```java
public void recur(int level, int param) { 
  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 
  process(level, param); 
  // drill down 
  recur( level: level + 1, newParam); 
  // restore current status 
}
```

###3.树的遍历

递归方式：
```java
    /**
     * 前序遍历
     * @param treeNode
     * @Param list
     * @return
     */
    public void preOrder(TreeNode treeNode, List<Integer> list) {
        if(null == treeNode) {
            return ;
        }

        list.add(treeNode.val);
        preOrder(treeNode.left, list);
        preOrder(treeNode.right, list);
    }

    /**
     * 中序遍历
     * @param treeNode
     * @Param list
     * @return
     */
    public void inOrder(TreeNode treeNode, List<Integer> list) {
        if(null == treeNode) {
            return;
        }
        inOrder(treeNode.left, list);
        list.add(treeNode.val);
        preOrder(treeNode.right, list);
    }

    /**
     * 后序遍历
     * @param treeNode
     * @Param list
     * @return
     */
    public void posOrder(TreeNode treeNode, List<Integer> list) {
        if(null == treeNode) {
            return ;
        }

        preOrder(treeNode.left, list);
        preOrder(treeNode.right, list);
        list.add(treeNode.val);
    }
```

数据栈方式：
```java
    /**
     * 前序遍历
     * @param treeNode
     * @return
     */
    public List<Integer> preOrder(TreeNode treeNode) {
        if(null == treeNode) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if(null != node.right) {
                stack.push(node.right);
            }

            if(null != node.left) {
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.empty()){
           if(root != null) {
               stack.push(root);
               root = root.left;
           } else {
               root = stack.pop();
               list.add(root.val);
               root = root.right;
           }

        }
        return list;
    }

    /**
     * 双栈后序遍历
     * @param treeNode
     * @return
     */
    public List<Integer> posOrderUnRecursion1(TreeNode treeNode) {
        if(null == treeNode) {
            return new ArrayList<>();
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(treeNode);

        while(!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            if(null != node.left) {
                stack1.push(node.left);
            }

            if(null != node.right) {
                stack1.push(node.right);
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        return result;
    }

    /**
     * 后序遍历
     * @param treeNode
     * @return
     */
    public List<Integer> posOrderUnRecursion2(TreeNode treeNode) {
        if(null == treeNode) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        TreeNode pre = treeNode;

        while(!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if(null != node.left && pre != node.left && pre != node.right) {
                stack.push(node.left);
            } else if(null != node.right && pre != node.right) {
                stack.push(node.right);
            } else {
                result.add(stack.pop().val);
                pre = node;
            }
        }

        return result;
    }

    /**
     * 广度优先遍历
     * @param root
     * @return
     */
    public List<Integer> bfs(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);
        List<Integer> result = new ArrayList<>();

        while(!deque.isEmpty()) {
            TreeNode node = deque.poll();
            result.add(node.val);

            if(null != node.left) {
                deque.add(node.left);
            }

            if(null != node.right) {
                deque.add(node.right);
            }
        }

        return result;
    }

```
###4.分治代码模版
```java
    public Object divide_conquer(problem, param1, param2, ...) {
        # recursion terminator
        if(Done) {
            return obj;
        }
        # prepare data
        data = prepare_data(problem);
        subproblems = split_problem(problem, data);
        # conquer subproblems
        subresult1 = this.divide_conquer(subproblems[0], p1, ...);
        subresult2 = this.divide_conquer(subproblems[1], p1, ...);
        subresult3 = this.divide_conquer(subproblems[2], p1, ...);

        # process and generate the final result
        result = process_result(subresult1, subresult2, subresult3, …);

        # revert the current level states
  }
```

