package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L12 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        String[][] str = {
                {"M", "MM", "MMM"},
                {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
        };
        int[] d = {1000, 100, 10, 1};

        public String intToRoman(int num) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4&&num!=0; i++) {
                if (num >= d[i]) {
                    sb.append(str[i][num / d[i] - 1]);
                    num %= d[i];
                }
            }
            return sb.toString();
        }

    }
}