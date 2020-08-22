package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L104 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public int maxDepth(TreeNode root) {
            return root==null?0:1+ Math.max(maxDepth(root.left),maxDepth(root.right));
        }
    }
}