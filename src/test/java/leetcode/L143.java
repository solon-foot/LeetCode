package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class L143 {
    private interface Solution {
        void reorderList(ListNode head);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        ListNode node = ListNode.create(1,2,3,4);
        solution.reorderList(node);
        assertEquals(ListNode.create(1,4,2,3), node);
        node = ListNode.create(1,2,3,4,5);
        solution.reorderList(node);
        assertEquals(ListNode.create(1,5,2,4,3), node);
        node = ListNode.create(1,2);
        solution.reorderList(node);
        assertEquals(ListNode.create(1,2), node);
        node = ListNode.create(1);
        solution.reorderList(node);
        assertEquals(ListNode.create(1), node);
    }

    private static class Solution1 implements Solution {

        @Override
        public void reorderList(ListNode head) {
            if (head==null)return;
            ListNode fast = head.next;
            ListNode slow = head;
            List<ListNode> list = new ArrayList<>();
            while (fast!=null && fast.next!=null){
                fast = fast.next.next;
                slow = slow.next;
            }
            while (slow!=null){
                list.add(slow);
                slow = slow.next;
            }
            slow = head;
            for (int i = list.size() - 1; i >= 0; i--) {
                ListNode node = list.get(i);
                node.next = slow.next;
                slow.next = node;
                slow = node.next;
            }
            if (slow!=null)slow.next = null;

        }
    }
}