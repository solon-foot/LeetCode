package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 147. 对链表进行插入排序
 * 对链表进行插入排序。
 *
 *
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 *
 *
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *
 *
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class L147 {
    private interface Solution {
        ListNode insertionSortList(ListNode head);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(ListNode.create(1,2,3,4), solution.insertionSortList(ListNode.create(4,2,1,3)));
        assertEquals(ListNode.create(-1,0,3,4,5), solution.insertionSortList(ListNode.create(-1,5,3,4,0)));

        int f1 = 0x0f113A;
        for (int i = 0; i < 16; i++) {
            TLog.e(i+1,int2ByteBig(f1+i*0x2000));
        }

    }   private static byte[] catche4 = new byte[4];
    private static byte[] int2Byte(int t){
        int i = 0;
        while (i<4){
            catche4[i++] = (byte) (t&0xFF);
            t>>=8;
        }
        return catche4;
    }
    private static byte[] int2ByteBig(int t){//大端
        int i = 3;
        while (i>=0){
            catche4[i--] = (byte) (t&0xFF);
            t>>=8;
        }
        return catche4;
    }
    private static class Solution1 implements Solution {

        @Override
        public ListNode insertionSortList(ListNode head) {
            if (head == null||head.next == null)return head;
            ListNode root = new ListNode(0);
            root.next = head;
            ListNode cursor = head.next;
            ListNode last = head;
            while (cursor!=null){
                if (last.val<=cursor.val) {
                    last = last.next;
                } else {
                    ListNode pre = root;
                    while (pre.next.val<=cursor.val)pre = pre.next;
                    last.next = cursor.next;
                    cursor.next = pre.next;
                    pre.next = cursor;
                }
                cursor = last.next;
            }
            return root.next;
        }
    }
}