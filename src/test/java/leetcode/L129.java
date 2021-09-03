package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 129. 求根到叶子节点数字之和
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 * 计算从根到叶子节点生成的所有数字之和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *     1
 *    / \
 *   2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 *
 * 输入: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 */
public class L129 {
    private interface Solution {
        int sumNumbers(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(25, solution.sumNumbers(TreeNode.create(1,2,3)));
        assertEquals(1026, solution.sumNumbers(TreeNode.create(4,9,0,5,1)));
    }

    private static class Solution1 implements Solution {

        @Override
        public int sumNumbers(TreeNode root) {
            return dfs(root,0);
        }
        int dfs(TreeNode root,int parentVal){
            if (root==null)return 0;
            parentVal = parentVal*10+ root.val;
            if (root.left == null && root.right == null) return parentVal;
            return dfs(root.left,parentVal) + dfs(root.right,parentVal);
        }
    }
}