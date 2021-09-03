package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class L234 {
    private interface Solution {
        boolean isPalindrome(ListNode head);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(false, solution.isPalindrome(ListNode.create(1,2)));
        assertEquals(true, solution.isPalindrome(ListNode.create(1,2,2,1)));
        assertEquals(true, solution.isPalindrome(ListNode.create(-129,-129)));
    }

    private static class Solution1 implements Solution {

        @Override
        public boolean isPalindrome(ListNode head) {
            List<Integer> arr = new ArrayList<>();
            while (head!=null){
                arr.add(head.val);
                head = head.next;
            }
            int i = 0,j = arr.size()-1;
            while (i<j) if (!arr.get(i++).equals(arr.get(j--)))return false;
            return true;
        }
    }
}