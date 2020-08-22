package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L392 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public boolean isSubsequence(String s, String t) {
            int index = -1;
            for (int j = 0; j < s.length(); j++) if ((index=t.indexOf(s.charAt(j),index+1))==-1)return false;
            return true;
        }
    }
}