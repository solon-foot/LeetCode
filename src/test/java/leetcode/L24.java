package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class L24 {
    private interface Solution {
        ListNode swapPairs(ListNode head);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(ListNode.create(2, 1, 4, 3), solution.swapPairs(ListNode.create(1, 2, 3, 4)));
        assertEquals(ListNode.create(2, 1, 3), solution.swapPairs(ListNode.create(1, 2, 3)));
        assertEquals(ListNode.create(2, 1), solution.swapPairs(ListNode.create(1, 2)));
        assertEquals(ListNode.create(1), solution.swapPairs(ListNode.create(1)));
        assertEquals(ListNode.create(), solution.swapPairs(ListNode.create()));
    }

    private static class Solution1 implements Solution {
        @Override
        public ListNode swapPairs(ListNode head) {
            ListNode node = head;
            ListNode next, pre = null;
            while (node != null && node.next != null) {
                next = node.next;
                node.next = next.next;
                next.next = node;
                if (pre == null) {
                    head = next;
                } else {
                    pre.next = next;
                }
                pre = node;
                node = node.next;
            }
            return head;
        }
    }

    private static class Solution2 implements Solution {

        @Override
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode next = head.next;
            head.next = swapPairs(next.next);
            next.next = head;
            return next;
        }
    }
}