package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 */
public class L1604 {
    private interface Solution {
        boolean isNumber(String s);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
//        assertEquals(true, solution.isNumber("+100"));
//        assertEquals(true, solution.isNumber("5e2"));
//        assertEquals(true, solution.isNumber("-123"));
//        assertEquals(true, solution.isNumber("3.1416"));
//        assertEquals(true, solution.isNumber("-1E-16"));
//        assertEquals(true, solution.isNumber("0123"));
//        assertEquals(true, solution.isNumber(".0123"));
//        assertEquals(true, solution.isNumber("0123."));
//        assertEquals(false, solution.isNumber("12e"));
//        assertEquals(false, solution.isNumber("1a3.14"));
//        assertEquals(false, solution.isNumber("1.2.3"));
//        assertEquals(false, solution.isNumber("+-5"));
//        assertEquals(false, solution.isNumber("-+5"));
//        assertEquals(false, solution.isNumber("12e+5.4"));
//        assertEquals(true, solution.isNumber("1 "));
//        assertEquals(true, solution.isNumber(" 1 "));
//        assertEquals(false, solution.isNumber("6+1"));
//        assertEquals(false, solution.isNumber(".-4"));
//        assertEquals(true, solution.isNumber("-1e-6"));
        assertEquals(true, solution.isNumber("32.e-80123"));
    }

    private static class Solution1 implements Solution {

        /**
         * ‘.’出现正确情况：只出现一次，且在e的前面
         *
         * ‘e’出现正确情况：只出现一次，且出现前有数字
         *
         * ‘+’‘-’出现正确情况：只能在开头和e后一位
         * @param s
         * @return
         */
        @Override
        public boolean isNumber(String s) {
            boolean dot = false;
            boolean e = false;
            boolean flag = false;
            boolean isNum = false;
            int len = s.length();
            int i = 0;
            while (i<len&&s.charAt(i)==' ')i++;
            while (i<len&&s.charAt(len-1)==' ')len--;

            for (; i < len; i++) {
                char c = s.charAt(i);
                if (c>='0' && c<='9'){
                    isNum = true;
                } else if (c == '.') {
                    if (dot||e)return false;
                    dot = true;
                } else if (c == 'e'||c=='E'){
                    if (!isNum||e)return false;
                    isNum = false;
                    e = true;
                    flag = false;
                    dot = false;
                } else if ((c=='+'||c=='-')){
                    if (flag || isNum || dot)return false;
                    flag = true;
                } else {
                    return false;
                }
            }
            return isNum;
        }
    }
    private static class Solution2 implements Solution {

        @Override
        public boolean isNumber(String s) {
            try {
                Double.parseDouble(s);
                return true;
            } catch (NumberFormatException e) {
            }
            return false;
        }
    }

}