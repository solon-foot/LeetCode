package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L26 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public int removeDuplicates(int[] nums) {
            int len = nums.length;
            if (len<2)return len;
            int c = nums[0];
            int index = 1;
            for (int i = 1; i < len; i++) {
                if (nums[i]!=c) nums[index++]=c= nums[i];
            }
            return index;
        }
    }
}