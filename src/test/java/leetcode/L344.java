package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L344 {
    private interface Solution {
        void reverseString(char[] s);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        char[]cs = "hello".toCharArray();
        solution.reverseString(cs);
        TLog.e(cs);
        assertArrayEquals("olleh".toCharArray(), cs);
        cs = "Hannah".toCharArray();
        solution.reverseString(cs);
        TLog.e(cs);
        assertArrayEquals("hannaH".toCharArray(), cs);
    }

    private static class Solution1 implements Solution {

        @Override
        public void reverseString(char[] s) {
            int len = s.length;
            int len2 = len>>1;
            len-=1;
            char c;
            for (int i = 0; i < len2; i++) {
                c = s[i];
                s[i] = s[len-i];
                s[len-i] = c;
            }
        }
    }
}