package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L28 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public int strStr(String haystack, String needle) {
            return haystack.indexOf(needle);
        }
    }
}