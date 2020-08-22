package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L1367 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public boolean isSubPath(ListNode head, TreeNode root) {
            if (root==null)return false;
            return isSubPath2(head,root)||isSubPath(head,root.left)||isSubPath(head,root.right);
        }
        public boolean isSubPath2(ListNode head, TreeNode root) {
            if (head == null)return true;
            if (root ==null)return false;
            if (head.val!=root.val)return false;
            return isSubPath2(head.next,root.left)|| isSubPath2(head.next,root.right);
        }
    }
}