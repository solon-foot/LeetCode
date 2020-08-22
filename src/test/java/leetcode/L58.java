package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L58 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
        TLog.e(solution.lengthOfLastWord("Hello World"));
        TLog.e(solution.lengthOfLastWord("Hello World  "));
        TLog.e(solution.lengthOfLastWord("World  "));
        TLog.e(solution.lengthOfLastWord("   World  "));
        TLog.e(solution.lengthOfLastWord("     "));
        TLog.e(solution.lengthOfLastWord("World"));
        TLog.e(solution.lengthOfLastWord("aa"));
        TLog.e(solution.lengthOfLastWord("a "));
        TLog.e(solution.lengthOfLastWord(" a "));
        TLog.e(solution.lengthOfLastWord(" a"));
        TLog.e(solution.lengthOfLastWord(""));
        TLog.e(solution.lengthOfLastWord(" "));
    }

    private static class Solution {
        public int lengthOfLastWord(String s) {
            int i = s.length(), len = 0;
            for(;i-- > 0 &&( (s.charAt(i--) == ' '|| (++len) >= 0)&&len==0 ););
            return len;
        }
    }
}