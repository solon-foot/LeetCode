package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L404 {
    private interface Solution {
        int sumOfLeftLeaves(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(24, solution.sumOfLeftLeaves(TreeNode.create(3, 9, 20, null, null, 15, 7)));
        TreeNode node = TreeNode.create(1,2,3,4,5);
        assertEquals(4, solution.sumOfLeftLeaves(node));
    }

    private static class Solution1 implements Solution {

        @Override
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) return 0;
            if (root.left!=null && root.left.left==null&&root.left.right==null) return root.left.val + sumOfLeftLeaves(root.right);
            return sumOfLeftLeaves(root.right)+sumOfLeftLeaves(root.left);
        }
    }
}