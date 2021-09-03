package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class L226 {
    private interface Solution {
        TreeNode invertTree(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(TreeNode.create(4,7,2,9,6,3,1), solution.invertTree(TreeNode.create(4,2,7,1,3,6,9)));
    }

    private static class Solution1 implements Solution {

        @Override
        public TreeNode invertTree(TreeNode root) {
            if (root==null)return null;
            TreeNode temp = invertTree(root.left);
            root.left = invertTree(root.right);
            root.right = temp;
            return root;
        }
    }
}