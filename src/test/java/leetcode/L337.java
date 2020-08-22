package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，
 * 每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L337 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(solution.rob(TreeNode.create(3,2,3,null,3,null,1)), 7);
        assertEquals(solution.rob(TreeNode.create(3,4,5,1,3,null,1)), 9);
        assertEquals(solution.rob(TreeNode.create(4,1,null,2,null,3)), 7);

    }

    private static class Solution {
        public int rob(TreeNode root) {
            int[] t = dfs(root);
            return Math.max(t[0],t[1]);
        }
        public int[] dfs(TreeNode root){
            if (root==null)return new int[]{0,0};
            int[] left = dfs(root.left);
            int[] right = dfs(root.right);
            return new int[]{root.val +left[1] + right[1],Math.max(left[0],left[1]) + Math.max(right[0],right[1])};

        }
    }
}