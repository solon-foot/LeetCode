package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L66 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public int[] plusOne(int[] digits) {
            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] != 9) {
                    digits[i]++;
                    return digits;
                }
                digits[i] = 0;
            }
            int[] copy = new int[digits.length + 1];
            copy[0] = 1;
            return copy;
        }
    }
}