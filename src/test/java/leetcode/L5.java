package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L5 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public String longestPalindrome(String s) {
            int len = 0;
            int slen = s.length();
            if (slen==0)return s;
            int currentIndex=0;
            boolean flag=false;
            for (int i = 0; i < slen-len; i++) {
                int k=1;
                while (i-k>=0&&i+k<slen&&s.charAt(i-k)==s.charAt(i+k))k++;
                int j=0;
                while (i-j>=0&&i+j+1<slen&&s.charAt(i-j)==s.charAt(i+j+1))j++;
//            j=j<<1;
                if (k>j){
                    if (k>len){
                        len = k;
                        currentIndex = i;
                        flag = true;
                    }
                } else {
                    if (j>len||flag&&j==len){
                        len = j;
                        currentIndex = i;
                        flag = false;
                    }
                }
            }
            return s.substring(currentIndex-len+1,currentIndex+len+(flag?0:1));
        }
    }
}