package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L572 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            if (s==null)return false;
            return isSameTree(s,t)||isSubtree(s.left,t)||isSubtree(s.right,t);
        }
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == q) return true;
            return p!=null&&q!=null&& p.val==q.val&& isSameTree(p.left,q.left)&& isSameTree(p.right,q.right);
        }
    }
}