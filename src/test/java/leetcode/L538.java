package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 *
 *
 * 例如：
 *
 * 输入: 原始二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 *
 *
 * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
 */
public class L538 {
    private interface Solution {
        TreeNode convertBST(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(TreeNode.create(18,20,13), solution.convertBST(TreeNode.create(5,2,13)));
    }

    private static class Solution1 implements Solution {


        @Override
        public TreeNode convertBST(TreeNode root) {
            total = 0;
            bst(root);
            return root;
        }
        int total = 0;
        void bst(TreeNode root){
            if (root==null)return;
            bst(root.right);
            total+=root.val;
            root.val = total;
            bst(root.left);
        }
    }
}