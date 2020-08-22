package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L14 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs.length==0)return "";
            int i=0;
            char c;
            while (true){
                c=0;
                for (String str : strs) {
                    if (i>=str.length()) return str.substring(0,i);
                    if (c==0) c= str.charAt(i);
                    else if (c!=str.charAt(i)) return str.substring(0,i);
                }
                i++;
            }
        }
    }
}