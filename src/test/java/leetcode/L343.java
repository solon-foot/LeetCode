package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L343 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        int[] pow3 = {1,3,9,27,81,243,729,2187,6561,19683,59049,177147,531441,1594323,4782969,14348907,43046721,129140163,387420489,1162261467};
        public int integerBreak(int n) {
            if (n == 2) return 1;
            if (n == 3) return 2;
            switch (n%3){
                case 1:
                    return pow3[n/3-1]<<2;
                case 2:
                    return pow3[n/3]<<1;
                default:
                    return pow3[n/3];
            }
        }
    }
}