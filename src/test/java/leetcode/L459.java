package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L459 {
    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(true, solution.repeatedSubstringPattern("abab"));
        assertEquals(false, solution.repeatedSubstringPattern("aba"));
        assertEquals(true, solution.repeatedSubstringPattern("abcabcabcabc"));
        assertEquals(false, solution.repeatedSubstringPattern("a"));
    }
    private interface  Solution{
        public boolean repeatedSubstringPattern(String s);
    }

    private static class Solution2 implements Solution {
        public boolean repeatedSubstringPattern(String s) {
            return (s + s).indexOf(s, 1) != s.length();
        }
    }
    private static class Solution1 implements Solution {
        int len;
        public boolean repeatedSubstringPattern(String s) {
            len = s.length();
            for (int i = 0; i < (len >> 1); i++) {
                if (repeated(s, i + 1)) return true;
            }
            return false;
        }

        public boolean repeated(String s, int subLen) {
            if (len% subLen != 0) return false;
            for (int i = 0; i < len; ) {
                for (int j = 0; j < subLen; j++, i++) {
                    if (s.charAt(i) != s.charAt(j)) return false;
                }
            }
            return true;
        }
    }
}