package com.goospy.leetcode.week;

import com.goospy.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class lee105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(null == preorder || null == inorder || preorder.length != inorder.length) {
            return null;
        }

        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, inorderIndexMap);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int pl, int pr, int il, int ir, Map<Integer, Integer> inorderIndexMap) {
        if(pl > pr || il > ir) {
            return null;
        }

        int preL = preorder[pl];
        TreeNode node = new TreeNode(preL);

        int inRootIndex = inorderIndexMap.get(preL);
        int leftSubTreeLen = inRootIndex - il;
        node.left = buildTree(preorder, inorder, pl + 1, pl + leftSubTreeLen, il, inRootIndex - 1, inorderIndexMap);
        node.right = buildTree(preorder, inorder, pl + leftSubTreeLen + 1, pr, inRootIndex + 1, ir, inorderIndexMap);
        return node;
    }

}
