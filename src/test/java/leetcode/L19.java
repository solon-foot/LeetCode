package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 */
public class L19 {
    private interface Solution {
        ListNode removeNthFromEnd(ListNode head, int n);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(null, solution.removeNthFromEnd(ListNode.create(1),1));
    }

    private static class Solution1 implements Solution {

        @Override
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null||n==0)return head;
            ListNode ans = new ListNode(0);
            ans.next = head;
            ListNode fast = ans;
            ListNode slow = ans;
            while (n-->0){
                fast = fast.next;
            }
            while (fast.next!=null){
                fast = fast.next;
                slow = slow.next;
            }
            slow.next = slow.next.next;
            return ans.next;
        }
    }
}