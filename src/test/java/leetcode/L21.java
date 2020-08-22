package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L21 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode head = new ListNode();
            ListNode cursor = head;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    cursor = cursor.next = l1;
                    l1 = l1.next;
                } else {
                    cursor = cursor.next = l2;
                    l2 = l2.next;
                }
            }
            cursor.next = l1==null?l2:l1;

            return head.next;
        }
    }
}