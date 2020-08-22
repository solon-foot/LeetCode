package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L114 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public void flatten(TreeNode root) {
            _flatten(root);
        }

        public TreeNode _flatten(TreeNode root) {
            if (root == null) return null;
            TreeNode t = _flatten(root.left);
            if (t != null){
                t.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            t = _flatten(root.right);
            if (t==null)return root;
            return t;
        }
    }
}