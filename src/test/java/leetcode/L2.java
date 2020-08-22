package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L2 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(9);
        TLog.e(l1);
        TLog.e(l2);
        TLog.e(solution.addTwoNumbers(l1, l2));
    }

    private static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(0);
            ListNode cursor = head;
            boolean flag = false;
            do {
                int t = flag ? 1 : 0;
                if (l1 != null) {
                    t += l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    t += l2.val;
                    l2 = l2.next;
                }
                cursor = cursor.next = new ListNode((flag = t > 9) ? t - 10 : t);
            } while (l1 != null || l2 != null || flag);

            return head.next;
        }
    }
}