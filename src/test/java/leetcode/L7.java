package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L7 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
        TLog.e(solution.reverse(123));
        TLog.e(solution.reverse(-123));
        TLog.e(solution.reverse(120));
//        TLog.e(Integer.MIN_VALUE);
//        TLog.e(Integer.MAX_VALUE);
        TLog.e(solution.reverse(-2147483648));
        TLog.e(solution.reverse(-2147483648));
    }

    private static class Solution {
        public int reverse(int x) {
            int q;
            long sum = 0;
            while (x!=0){
                q = x / 10;
                sum = ((sum << 3) + (sum << 1)) + x - ((q << 3) + (q << 1));
                x = q;
            }
            return sum>0x7fffffff||sum<0x80000000?0:(int)sum;

        }
    }
}