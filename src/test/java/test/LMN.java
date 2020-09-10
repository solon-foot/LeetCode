package test;

import leetcode.ListNode;
import leetcode.TLog;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * 约瑟夫问题
 */
public class LMN {
    private interface Solution {
        int find(int n, int m);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        Solution solution2 = new Solution2();
        assertEquals(2, solution.find(3, 4));
        assertEquals(3, solution.find(3, 1));
        assertEquals(3, solution.find(3, 2));//3
        assertEquals(1, solution.find(3, 5));//1
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
//                TLog.e(i, j, solution.find(i, j));
                assertEquals(solution.find(i,j),solution2.find(i,j));
            }
        }

//        System.out.println(Integer.parseInt("0x12"));
        try {
            System.out.println(InetAddress.getByName("192.168.0.012").getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static class Solution1 implements Solution {
        public boolean validateIPv4(String IP) {
            String[] nums = IP.split("\\.", -1);
            if (nums.length!=4)return false;
            for (String x : nums) {
                // Validate integer in range (0, 255):
                // 1. length of chunk is between 1 and 3
                if (x.length() == 0 || x.length() > 3) return false;
                // 2. no extra leading zeros
                if (x.charAt(0) == '0' && x.length() != 1) return false;
                // 3. only digits are allowed
                for (char ch : x.toCharArray()) {
                    if (! Character.isDigit(ch)) return false;
                }
                // 4. less than 255
                if (Integer.parseInt(x) > 255) return false;
            }
            return true;
        }

        @Override
        public int find(int n, int m) {
            if (m == 1) return n;
            ListNode head = new ListNode(1);
            ListNode cursor = head;
            for (int i = 1; i < n; i++) {
                cursor = cursor.next = new ListNode(i + 1);
            }
            cursor.next = head;
            cursor = head;
            while (cursor.next != cursor) {
                for (int i = 1; i < m - 1; i++) {
                    cursor = cursor.next;
                }
                cursor = cursor.next = cursor.next.next;
            }
            return cursor.val;
        }
    }

    private static class Solution2 implements Solution {

        @Override
        public int find(int n, int m) {
            int s = 0;

            for (int i = 2; i <= n; ++i) {
                s = (s + m) % i;
            }
            return s + 1;
        }
    }
}