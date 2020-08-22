package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 */
public class L111 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(2, solution.minDepth(TreeNode.create(3,9,20,null,null,15,7)));
    }

    private static class Solution {
        public int minDepth(TreeNode root) {
            if (root==null)return 0;
            int t = 0;
            if (root.left!=null)t=minDepth(root.left);
            if (root.right!=null) t = Math.min(t,minDepth(root.right));
            return t==0?1:t+1;

        }
        public int minDepth(TreeNode root,int deep,int min) {
            if (deep>=min)return min;
            int t = 0;
            if (root.left!=null) t = minDepth(root.left,deep+1,min);
            if (root.right!=null) t= minDepth(root.left);
            t = t==0?1:t+1;
            min = Math.min(t,min);
            return min;
        }
    }
}