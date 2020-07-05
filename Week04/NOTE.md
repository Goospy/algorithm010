#学习笔记

##深度优先搜索

深度优先遍历是指优先遍历树或者图尽可能深的分支，直至叶子节点，然后再回溯其他节点。

DFS代码模版：
```java
    /**
     * 深度优先遍历 -- 数据栈版
     * @param root
     * @return
     */
    public List<Integer> dfs(TreeNode root) {
        Stack<TreeNode> treeNodes = new Stack<>();
        treeNodes.push(root);
        List<Integer> result = new ArrayList<>();

        while(!treeNodes.isEmpty()) {
            TreeNode node = deque.poll();
            result.add(node.val);

            if(null != node.right) {
                treeNodes.push(node.right);
            }

            if(null != node.left) {
                treeNodes.push(node.left);
            }
        }

        return result;
    }

    /**
     * 深度优先遍历 -- 递归版
     * @param root
     * @return
     */
    public void dfs(TreeNode root, Set<TreeNode> visited) {
        if(null == root || visited.contains(root)) {
            return;
        }

        System.out.println(root.val);
        visited.add(root);
        dfs(root.left, visited);
        dfs(root.right, visited);
    }

```

##广度优先搜索

广度优先遍历是指按度的层数划分来遍历树或图。

BFS代码模版：
```java
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

##贪心算法

贪心算法是指在解决全局父问题时，子问题都选择最优解来组合成全局问题的最优解，具有一定的局限性，在全局最优解不完全由子问题的最优解组成时，此算法失效。


##二分查找

二分查找采用的是减治的思想，通过不断折半查找缩短区间，从而得到答案。

```java
    public int search(int[] nums, int target) {
        if(null == nums) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left)/2;

            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }       
        }
        return -1;
    }
```

####使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

核心思想：

如果 [l, mid - 1] 是有序数组，且 target 的大小满足在[l, mid]区间，则我们应该将搜索范围缩小至 [l, mid - 1]，否则在 [mid + 1, r] 中寻找。
如果 [mid, r] 是有序数组，且 target 的大小满足在[mid, r]区间，则我们应该将搜索范围缩小至 [mid + 1, r]，否则在 [l, mid - 1] 中寻找。

```java
    public int search(int[] nums, int target) {
        if(null == nums) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left)/2;

            if(nums[mid] == target) {
                return mid;
            }

            if(nums[left] <= nums[mid]) {
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if(nums[right] >= target && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
```
