package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L13 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public int romanToInt(String s) {
            int sum=0;
            int len = s.length();
            for (int i=0; i < len; i++) {
                char c = s.charAt(i);
                char n = 0;
                if (i < s.length() - 1) n = s.charAt(i + 1);
                switch (c) {
                    case 'I':
                        if (n == 'V') {
                            sum += 4;
                            i++;
                        } else if (n == 'X') {
                            sum += 9;
                            i++;
                        } else sum += 1;
                        break;

                    case 'V':
                        sum += 5;
                        break;
                    case 'X':
                        if (n == 'L') {
                            sum += 40;
                            i++;
                        } else if (n == 'C') {
                            sum += 90;
                            i++;
                        } else sum += 10;
                        break;
                    case 'L':
                        sum += 50;
                        break;
                    case 'C':
                        if (n == 'D') {
                            sum += 400;
                            i++;
                        } else if (n == 'M') {
                            sum += 900;
                            i++;
                        } else sum += 100;
                        break;
                    case 'D':
                        sum += 500;
                        break;
                    case 'M':
                        sum += 1000;
                        break;
                }
            }
            return sum;
        }
    }
}