package com.goospy.leetcode.week;

import com.goospy.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class lee236 {


    private TreeNode ans = null;
    /**
     * 解法1：最近公共祖先分三种情况：p、q是子节点，是p、是q
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        isParent(root, p, q);
        return ans;
    }

    private boolean isParent(TreeNode root, TreeNode p, TreeNode q) {
        if(null == root) {
            return false;
        }

        boolean lson = isParent(root.left, p, q);
        boolean rson = isParent(root.right, p, q);

        boolean isMatch = (lson && rson) || (root.val == p.val || root.val == q.val) && (lson || rson);
        if(isMatch) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    /**
     * 解法2：记录p、q的父节点序列，第一个相交的节点就是最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, TreeNode> map = new HashMap<>();
        dfs(root, map);

        Set<Integer> visited = new HashSet<>();
        while(p != null) {
            visited.add(p.val);
            p = map.get(p.val);
        }

        while(q != null) {
            if(visited.contains(q.val)) {
                return q;
            }
            q = map.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root, Map<Integer, TreeNode> map) {
        if(null != root.left) {
            map.put(root.left.val, root);
            dfs(root.left, map);
        }

        if(null != root.right) {
            map.put(root.right.val, root);
            dfs(root.right, map);
        }
    }

}
