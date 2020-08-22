package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L415 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public String addStrings(String num1, String num2) {
            int i = num1.length();
            int j = num2.length();
            int k = Math.max(i, j) + 1;
            char[] nums = new char[k];
            boolean flag = false;
            char t;
            while (i > 0 || j > 0) {
                t = flag?'1':'0';
                if (i > 0) t += num1.charAt(--i)-'0';
                if (j > 0) t += num2.charAt(--j)  - '0';
                flag = t > '9';
                if (flag) t -= 10;
                nums[--k] = t;
            }
            if (flag)nums[--k] = '1';
            return new String(nums,k,nums.length-k);

        }
    }
}