package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L3 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public int lengthOfLongestSubstring(String s) {
//        char[] arr = s.toCharArray();
//        int res = 0;
//        for (int i = 0; i + res < arr.length; i++) {
//            int j = i + 1;
//            boolean[] b = new boolean[256];
//            b[arr[i]]=true;
//            for (; j < arr.length; j++) {
//                char c = arr[j];
//                if (b[c])break ;
//                b[c] = true;
//            }
//            res = Math.max(res, j - i);
//
//        }
//        return res;
            int[] last = new int[128];
            int n = s.length();
            int res = 0;
            int start = 0; // 窗口开始位置
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                start = Math.max(start, last[c]);
                res   = Math.max(res, i - start + 1);
                last[c] = i+1;
            }
            return res;
        }

    }
}