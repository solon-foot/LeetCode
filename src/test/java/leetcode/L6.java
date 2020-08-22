package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L6 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
        TLog.e(solution.convert("LEETCODEISHIRING", 3));
        TLog.e("LCIRETOESIIGEDHN");
        TLog.e(solution.convert("LEETCODEISHIRIN", 4));
        TLog.e("LDREOEIIECIHNTSG");
        TLog.e(solution.convert("A", 1));
    }

    private static class Solution {
        public String convert(String s, int numRows) {
            int c = (numRows <<1)  - 2;
            if (c==0)return s;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                for (int j = i; j < s.length(); j += c) {
                    if (i == 0 || i == numRows - 1)
                        sb.append(s.charAt(j));
                    else {
                        sb.append(s.charAt(j));
                        if (j+c-i-i<s.length())
                            sb.append(s.charAt(j + c -i- i));
                    }
                }
            }
            return sb.toString();
        }
    }
}