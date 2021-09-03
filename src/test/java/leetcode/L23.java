package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */
public class L23 {
    private interface Solution {
        ListNode mergeKLists(ListNode[] lists);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(ListNode.create(1,1,2,3,4,4,5,6), solution.mergeKLists(new ListNode[]{ListNode.create(1,4,5),ListNode.create(1,3,4),ListNode.create(2,6)}));
        assertEquals(null, solution.mergeKLists(new ListNode[]{}));
        assertEquals(null, solution.mergeKLists(new ListNode[]{ListNode.create()}));
    }

    private static class Solution1 implements Solution {

        @Override
        public ListNode mergeKLists(ListNode[] lists) {
            boolean flag = true;
            ListNode ans = new ListNode(0);
            ListNode temp = ans;
            while (flag){
                int min = Integer.MAX_VALUE;
                int index=0;
                flag = false;
                for (int i = 0; i < lists.length; i++) {
                    ListNode node = lists[i];
                    if (node==null)continue;
                    flag = true;
                    if (node.val<min){
                        index = i;
                        min = node.val;
                    }
                }
                if (flag){
                    ListNode node = lists[index];
                    lists[index] = node.next;
                    temp = temp.next = node;
                }

            }
            return ans.next;
        }
    }
    private static class Solution2 implements Solution {

        @Override
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length==0)return null;
            ListNode ans = lists[0];
            if (lists.length==1)return ans;
            for (int i = 1; i < lists.length; i++) {
                ans = mergeList(ans,lists[i]);
            }
            return ans;
        }
        ListNode mergeList(ListNode n1,ListNode n2){
            ListNode root = new ListNode(-1);
            ListNode p = root;
            while (n1 != null && n2 != null) {
                if (n1.val <= n2.val) {
                    p.next = n1;
                    n1 = n1.next;
                } else {
                    p.next = n2;
                    n2 = n2.next;
                }
                p = p.next;
            }
            if (n1 != null) {
                p.next = n1;
            }
            if (n2 != null) {
                p.next = n2;
            }
            return root.next;
        }
    }
}