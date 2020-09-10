package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class L206 {
    private interface Solution {
        ListNode reverseList(ListNode head);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(ListNode.create(5,4,3,2,1), solution.reverseList(ListNode.create(1,2,3,4,5)));
    }

    private static class Solution1 implements Solution {

        @Override
        public ListNode reverseList(ListNode head) {
            ListNode cursor = null;
            ListNode pre;
            while (head!=null){
                 pre = new ListNode(head.val);
                 pre.next = cursor;
                 cursor = pre;
                head = head.next;
            }
            return cursor;
        }
    }
    private static class Solution2 implements Solution {

        @Override
        public ListNode reverseList(ListNode head) {
            ListNode cursor = null;
            while (head!=null){
                ListNode next = head.next;
                head.next = cursor;
                cursor = head;
                head = next;
            }
            return cursor;
        }
    }
}