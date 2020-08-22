package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 */
public class L110 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, solution.isBalanced(TreeNode.create("[3,9,20,null,null,15,7]")));
        assertEquals(false, solution.isBalanced(TreeNode.create("[1,2,2,3,3,null,null,4,4]")));
    }

    private static class Solution {
        public boolean isBalanced(TreeNode root) {
            try {
                height(root);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        public int height(TreeNode root){
            if (root == null)return 0;
            int left = height(root.left);
            int right = height(root.right);
            if (Math.abs(left-right)>1)throw new RuntimeException("");
            return Math.max(left,right)+1;
        }
    }
}