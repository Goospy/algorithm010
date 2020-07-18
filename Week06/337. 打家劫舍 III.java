/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public int rob(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }

    private Map<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {
        if(null == root) {
            return 0;
        }

        if(map.keySet().contains(root)) {
            return map.get(root);
        }

        int rootSum = root.val;
        if(null != root.left) {
            rootSum += rob(root.left.left);
            rootSum += rob(root.left.right);
        }

        if(null != root.right) {
            rootSum += rob(root.right.left);
            rootSum += rob(root.right.right);
        }

        int childSum = rob(root.left) + rob(root.right);
        int res = Math.max(rootSum, childSum);
        map.put(root, res);
        return res;
    }
}