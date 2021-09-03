package leetcode;

import org.junit.Test;

import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * 530. 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *
 */
public class L530 {
    private interface Solution {
        int getMinimumDifference(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(1, solution.getMinimumDifference(new TreeNode(1,null,new TreeNode(3,new TreeNode(2),null))));
    }

    private static class Solution1 implements Solution {

        @Override
        public int getMinimumDifference(TreeNode root) {
            dfs(root);
            return ans;
        }
        int ans = Integer.MAX_VALUE;
        Integer last = null;
        void dfs(TreeNode root) {
            if (root == null)return;

            dfs(root.left);
            if (last != null){
                ans = Math.min(ans,root.val-last);
            }

            last = root.val;
            dfs(root.right);
        }
    }
}