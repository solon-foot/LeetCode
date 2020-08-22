package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L9 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
        TLog.e(solution.isPalindrome(10));
        TLog.e(solution.isPalindrome(121));
        TLog.e(solution.isPalindrome(-121));
    }

    private static class Solution {
        public boolean isPalindrome(int x) {
            if (x<0)return false;
            int q;
            int sum = 0;
            int temp = x;
            while (x!=0){
                q = x / 10;
                sum = ((sum << 3) + (sum << 1)) + x - ((q << 3) + (q << 1));
                x = q;
            }
            return temp==sum;
        }
    }
}