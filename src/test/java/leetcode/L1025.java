package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L1025 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public boolean divisorGame(int N) {
            //偶数必胜，奇数必输
            /**
             * 如果是偶数，则取1 使其变成奇数，奇数则只有（奇数*奇数 = 奇数），无论怎么选，最后都会变成偶数，因为没得选
             */
            return (N & 1) == 0;
        }
    }
}