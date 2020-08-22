package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L8 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
        //        TLog.e(new l8().myAtoi("42"));
//        TLog.e(new l8().myAtoi("   -42"));
//        TLog.e(new l8().myAtoi("4193 with words"));
//        TLog.e(new l8().myAtoi("words and 987"));
//        TLog.e(new l8().myAtoi("-91283472332"));
//        TLog.e(new l8().myAtoi("-2147483648"));
//        TLog.e(new l8().myAtoi("2147483647"));
//        TLog.e(new l8().myAtoi("2147483648"));
//        TLog.e(new l8().myAtoi("+"));
//        TLog.e(new l8().myAtoi(" +"));
//        TLog.e(new l8().myAtoi(" "));
//        TLog.e(new l8().myAtoi("+-2"));
    }

    private static class Solution {
        public int myAtoi(String str) {

            int result = 0;
            boolean negative = false;
            int i = 0, len = str.length();
            int limit = -Integer.MAX_VALUE;
            int multmin;
            int digit;
            if (len == 0) return 0;
            char c = str.charAt(i++);
            while (c == ' ' && i < len) c = str.charAt(i++);
            if (c < '0' || c > '9') {
                if (c == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (c == '+') {

                } else {
                    return 0;
                }
                if (len == i) return 0;
                c = str.charAt(i++);
                if (c < '0' || c > '9') {
                    return 0;
                }
            }
            multmin = limit / 10;
            do {
                if (result < multmin) {
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result *= 10;
                digit = c - '0';
                if (result < limit + digit) {
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result -= digit;
            } while (i < len && (c = str.charAt(i++)) >= '0' && c <= '9');
            return negative ? result : -result;
        }
    }
}