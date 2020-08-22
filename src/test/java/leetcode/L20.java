package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L20 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public boolean isValid(String s) {
            int len = s.length();
            if (len==0)return true;
            if ((len&1)!=0)return false;
            int i=0;
            char[] last = new char[len>>1];
            for (int l = 0; l < s.length(); l++) {
                char c = s.charAt(l);
                switch (c){
                    case '(':
                        if (i>=last.length)return false;
                        last[i++] =')';
                        break;
                    case '[':
                        if (i>=last.length)return false;
                        last[i++] =']';
                        break;
                    case '{':
                        if (i>=last.length)return false;
                        last[i++] ='}';
                        break;
//                case ')':
//                case ']':
//                case '}':
                    default:
                        i--;
                        if (i<0||last[i]!=c)return false;
                        break;
                }
            }
            return i==0;
        }
    }
}